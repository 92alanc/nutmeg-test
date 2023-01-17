package com.alancamargo.nutmegtest.albums.data.local

import com.alancamargo.nutmegtest.albums.data.database.AlbumDao
import com.alancamargo.nutmegtest.albums.data.mapping.toDb
import com.alancamargo.nutmegtest.albums.data.mapping.toDomain
import com.alancamargo.nutmegtest.albums.domain.model.Album
import com.alancamargo.nutmegtest.core.log.Logger
import javax.inject.Inject

internal class AlbumLocalDataSourceImpl @Inject constructor(
    private val dao: AlbumDao,
    private val logger: Logger
) : AlbumLocalDataSource {

    override suspend fun getAlbums(): List<Album> {
        return dao.getAlbums()?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun saveAlbum(album: Album) {
        try {
            val dbAlbum = album.toDb()
            dao.insertAlbum(dbAlbum)
        } catch (t: Throwable) {
            logger.error(t)
        }
    }
}
