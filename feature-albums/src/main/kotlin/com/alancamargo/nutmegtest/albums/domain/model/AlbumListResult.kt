package com.alancamargo.nutmegtest.albums.domain.model

internal sealed class AlbumListResult {

    data class Success(val albums: List<Album>) : AlbumListResult()

    object Empty : AlbumListResult()

    object NetworkError : AlbumListResult()

    object ServerError : AlbumListResult()

    object GenericError : AlbumListResult()
}
