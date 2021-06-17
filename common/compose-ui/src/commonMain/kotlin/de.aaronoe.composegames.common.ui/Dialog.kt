package de.aaronoe.composegames.common.ui

import androidx.compose.runtime.Composable

@Composable
expect fun Dialog(
    title: String,
    text: String?,
    confirmText: String,
    confirmAction: () -> Unit
)