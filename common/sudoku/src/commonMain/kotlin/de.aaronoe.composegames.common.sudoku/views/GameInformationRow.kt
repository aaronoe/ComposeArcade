package de.aaronoe.composegames.common.sudoku.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import de.aaronoe.composegames.common.sudoku.viewmodel.GameState

@Composable
fun GameInformationRow(state: GameState) {
    CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.caption) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = state.difficulty.name)
            Text(text = "Mistakes ${state.mistakes}/3")
            Text(text = state.elapsedTimeString)
        }
    }
}