package de.aaronoe.composegames.common.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun Dialog(
    title: String,
    text: String?,
    confirmText: String,
    confirmAction: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = {},
        title = { Text(text = title) },
        text = { text?.let { Text(it) } },
        confirmButton = {
            TextButton(onClick = confirmAction) {
                Text(text = confirmText)
            }
        },
    )
}