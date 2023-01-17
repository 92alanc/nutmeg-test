package com.alancamargo.nutmegtest.albums.data.local

import com.alancamargo.nutmegtest.albums.domain.model.Album

internal interface AlbumLocalDataSource {

    suspend fun getAlbums(): List<Album>

    suspend fun saveAlbum(album: Album)
}
