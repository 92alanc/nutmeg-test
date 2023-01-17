package com.alancamargo.nutmegtest.albums.data.remote

import com.alancamargo.nutmegtest.albums.data.mapping.toDomain
import com.alancamargo.nutmegtest.albums.data.service.AlbumService
import com.alancamargo.nutmegtest.albums.domain.model.Album
import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import com.alancamargo.nutmegtest.core.extensions.isServerError
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class AlbumRemoteDataSourceImpl @Inject constructor(
    private val service: AlbumService
) : AlbumRemoteDataSource {

    override suspend fun getAlbums(): List<Album> {
        val albumResponseList = service.getAlbums()

        return albumResponseList.map { albumResponse ->
            val userName = service.getUser(albumResponse.userId).userName
            val thumbnailUrl = service.getPhotos(albumResponse.id).first().thumbnailUrl

            albumResponse.toDomain(
                userName = userName,
                thumbnailUrl = thumbnailUrl
            )
        }
        /*return try {
            // get albums

            if (albums.isEmpty()) {
                AlbumListResult.Empty
            } else {
                AlbumListResult.Success(albums)
            }
        } catch (t: Throwable) {
            handleError(t)
        }*/
    }

    private fun handleError(t: Throwable) = when (t) {
        is HttpException -> {
            if (t.isServerError()) {
                AlbumListResult.ServerError
            } else {
                AlbumListResult.GenericError
            }
        }

        is IOException -> AlbumListResult.NetworkError

        else -> AlbumListResult.GenericError
    }
}
