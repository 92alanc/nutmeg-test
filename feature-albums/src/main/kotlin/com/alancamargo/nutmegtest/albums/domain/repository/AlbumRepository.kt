package com.alancamargo.nutmegtest.albums.domain.repository

import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult

internal interface AlbumRepository {

    suspend fun getAlbums(): AlbumListResult
}
