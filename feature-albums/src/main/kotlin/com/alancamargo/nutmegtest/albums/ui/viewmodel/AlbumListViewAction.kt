package com.alancamargo.nutmegtest.albums.ui.viewmodel

import com.alancamargo.nutmegtest.albums.ui.model.AlbumListError

internal sealed class AlbumListViewAction {

    data class ShowErrorDialogue(val error: AlbumListError) : AlbumListViewAction()

    object ShowAppInfo : AlbumListViewAction()
}
