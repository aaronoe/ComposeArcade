package de.aaronoe.composegames.common.sudoku.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.aaronoe.composegames.common.sudoku.viewmodel.GameViewModel
import de.aaronoe.composegames.utils.mavericks.collectAsState

@Composable
fun SudokuGame(viewModel: GameViewModel) {
    val state by viewModel.collectAsState()

    GameStateDialogs(
        isGameOver = state.isGameOver,
        isGameComplete = state.isGameComplete,
        mistakes = state.mistakes,
        resetGameAction = viewModel::resetGame
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Column {
            GameInformationRow(state)
            Spacer(modifier = Modifier.height(4.dp))
            SudokuField(viewModel)
        }

        Column {
            Spacer(modifier = Modifier.height(48.dp))
            GameActions(viewModel = viewModel)
            Spacer(modifier = Modifier.height(24.dp))
            NumberSelection(state.missingNumbers) {
                viewModel.setNumber(it)
            }
        }
    }
}

@Composable
private fun GameStateDialogs(
    isGameOver: Boolean,
    isGameComplete: Boolean,
    mistakes: Int,
    resetGameAction: () -> Unit
) {
    if (isGameOver) {
        GameOverDialog(
            text = "Game Over",
            title = "You made $mistakes mistakes",
            confirmText = "Restart game",
            confirmAction = resetGameAction
        )
    }

    if (isGameComplete) {
        GameOverDialog(
            title = "Game Complete",
            text = null,
            confirmText = "Back to menu",
            confirmAction = resetGameAction
        )
    }
}