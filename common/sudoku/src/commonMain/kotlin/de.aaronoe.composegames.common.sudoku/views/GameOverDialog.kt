package de.aaronoe.composegames.common.sudoku.views

import androidx.compose.runtime.Composable

@Composable
fun GameOverDialog(
    title: String,
    text: String?,
    confirmText: String,
    confirmAction: () -> Unit
) {
    // TODO: figure out how to use dialogs on desktop
    /*AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = {},
        title = { Text(text = title) },
        text = { text?.let { Text(it) } },
        confirmButton = {
            TextButton(onClick = confirmAction) {
                Text(text = confirmText)
            }
        },
    )*/
}