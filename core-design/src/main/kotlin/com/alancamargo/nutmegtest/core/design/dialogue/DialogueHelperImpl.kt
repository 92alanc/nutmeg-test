package com.alancamargo.nutmegtest.core.design.dialogue

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
class DialogueHelperImpl @Inject internal constructor() : DialogueHelper {

    override fun showDialogue(
        context: Context,
        iconRes: Int,
        titleRes: Int,
        messageRes: Int,
        buttonTextRes: Int,
        onButtonClicked: (() -> Unit)?
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(titleRes)
            .setIcon(iconRes)
            .setMessage(messageRes)
            .setNeutralButton(buttonTextRes) { _, _ ->
                onButtonClicked?.invoke()
            }.setCancelable(false)
            .show()
    }
}
