package de.aaronoe.composegames.common.sudoku.views

import androidx.compose.runtime.Composable
import de.aaronoe.composegames.common.ui.Dialog

@Composable
fun GameOverDialog(
    title: String,
    text: String?,
    confirmText: String,
    confirmAction: () -> Unit
) {
    Dialog(
        title = title,
        text = text,
        confirmText = confirmText,
        confirmAction = confirmAction
    )
}