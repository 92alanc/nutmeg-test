package com.alancamargo.nutmegtest.albums.data.repository

import com.alancamargo.nutmegtest.albums.data.local.AlbumLocalDataSource
import com.alancamargo.nutmegtest.albums.data.remote.AlbumRemoteDataSource
import com.alancamargo.nutmegtest.albums.domain.model.Album
import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import com.alancamargo.nutmegtest.albums.domain.repository.AlbumRepository
import com.alancamargo.nutmegtest.core.extensions.isServerError
import com.alancamargo.nutmegtest.core.log.Logger
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class AlbumRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumRemoteDataSource,
    private val localDataSource: AlbumLocalDataSource,
    private val logger: Logger
) : AlbumRepository {

    override suspend fun getAlbums(): AlbumListResult {
        return try {
            val albums = remoteDataSource.getAlbums().onEach { album ->
                localDataSource.saveAlbum(album)
            }

            handleSuccess(albums)
        } catch (t: Throwable) {
            logger.error(t)
            fetchFromDatabase(t)
        }
    }

    private suspend fun fetchFromDatabase(remoteOperationException: Throwable): AlbumListResult {
        return try {
            val albums = localDataSource.getAlbums()
            handleSuccess(albums)
        } catch (t: Throwable) {
            logger.error(t)
            handleError(remoteOperationException)
        }
    }

    private fun handleSuccess(albums: List<Album>) = if (albums.isEmpty()) {
        AlbumListResult.Empty
    } else {
        AlbumListResult.Success(albums)
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
