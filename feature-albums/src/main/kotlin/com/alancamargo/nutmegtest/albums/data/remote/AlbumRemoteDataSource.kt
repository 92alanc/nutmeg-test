package com.alancamargo.nutmegtest.albums.data.remote

import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult

internal interface AlbumRemoteDataSource {

    suspend fun getAlbums(): AlbumListResult
}
