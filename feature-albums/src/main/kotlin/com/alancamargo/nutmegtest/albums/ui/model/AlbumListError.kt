package com.alancamargo.nutmegtest.albums.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alancamargo.nutmegtest.core.design.R

internal enum class AlbumListError(
    @DrawableRes val iconRes: Int,
    @StringRes val messageRes: Int
) {

    NO_RESULTS(
        iconRes = R.drawable.ic_no_results,
        messageRes = R.string.error_no_results
    ),
    DISCONNECTED(
        iconRes = R.drawable.ic_disconnected,
        messageRes = R.string.error_disconnected
    ),
    SERVER(
        iconRes = R.drawable.ic_server_error,
        messageRes = R.string.error_server
    ),
    GENERIC(
        iconRes = R.drawable.ic_generic_error,
        messageRes = R.string.error_generic
    )
}
