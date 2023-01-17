package com.alancamargo.nutmegtest.albums.data.remote

import com.alancamargo.nutmegtest.albums.data.mapping.toDomain
import com.alancamargo.nutmegtest.albums.data.service.AlbumService
import com.alancamargo.nutmegtest.albums.domain.model.Album
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
    }
}
