package com.alancamargo.nutmegtest.albums.data.local

import com.alancamargo.nutmegtest.albums.data.database.AlbumDao
import com.alancamargo.nutmegtest.albums.data.mapping.toDb
import com.alancamargo.nutmegtest.albums.data.mapping.toDomain
import com.alancamargo.nutmegtest.albums.domain.model.Album
import javax.inject.Inject

internal class AlbumLocalDataSourceImpl @Inject constructor(
    private val dao: AlbumDao
) : AlbumLocalDataSource {

    override suspend fun getAlbums(): List<Album> {
        return dao.getAlbums()?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun saveAlbum(album: Album) {
        val dbAlbum = album.toDb()
        dao.insertAlbum(dbAlbum)
    }
}
