package com.alancamargo.nutmegtest.albums.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal enum class AlbumListError(
    @DrawableRes val iconRes: Int,
    @StringRes val messageRes: Int
) {

    NO_RESULTS(
        iconRes = 0,
        messageRes = 0
    ),
    DISCONNECTED(
        iconRes = 0,
        messageRes = 0
    ),
    SERVER(
        iconRes = 0,
        messageRes = 0
    ),
    GENERIC(
        iconRes = 0,
        messageRes = 0
    )
}
