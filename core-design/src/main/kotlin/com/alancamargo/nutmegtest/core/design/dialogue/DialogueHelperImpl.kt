package com.alancamargo.nutmegtest.core.design.dialogue

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

internal class DialogueHelperImpl @Inject constructor() : DialogueHelper {

    override fun showDialogue(
        context: Context,
        iconRes: Int,
        titleRes: Int,
        messageRes: Int,
        buttonTextRes: Int
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(titleRes)
            .setIcon(iconRes)
            .setMessage(messageRes)
            .setNeutralButton(buttonTextRes, null)
            .setCancelable(false)
            .show()
    }
}
