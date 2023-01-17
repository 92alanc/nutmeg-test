package com.alancamargo.nutmegtest.albums.data.remote

import com.alancamargo.nutmegtest.albums.domain.model.Album

internal interface AlbumRemoteDataSource {

    suspend fun getAlbums(): List<Album>
}
