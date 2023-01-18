package com.alancamargo.nutmegtest.core.design.dialogue

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface DialogueHelper {

    fun showDialogue(
        context: Context,
        @DrawableRes iconRes: Int,
        @StringRes titleRes: Int,
        @StringRes messageRes: Int,
        @StringRes buttonTextRes: Int,
        onButtonClicked: (() -> Unit)? = null
    )
}
