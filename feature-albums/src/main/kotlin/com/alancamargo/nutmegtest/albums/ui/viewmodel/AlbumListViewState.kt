package com.alancamargo.nutmegtest.albums.ui.viewmodel

import com.alancamargo.nutmegtest.albums.domain.model.Album

internal data class AlbumListViewState(
    val isLoading: Boolean = false,
    val albums: List<Album>? = null
) {

    fun onLoading() = copy(isLoading = true, albums = null)

    fun onFinishedLoading() = copy(isLoading = false)

    fun onAlbumsReceived(albums: List<Album>) = copy(albums = albums)
}
