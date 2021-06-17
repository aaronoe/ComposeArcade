package de.aaronoe.composegames.common.sudoku.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.aaronoe.composegames.common.sudoku.viewmodel.GameState
import de.aaronoe.composegames.common.sudoku.viewmodel.GameViewModel
import de.aaronoe.composegames.utils.mavericks.collectAsState

@Composable
fun SudokuGame(
    viewModel: GameViewModel,
    backToMenuAction: () -> Unit
) {
    val state by viewModel.collectAsState()

    GameStateDialogs(
        isGameOver = state.isGameOver,
        isGameComplete = state.isGameComplete,
        mistakes = state.mistakes,
        resetGameAction = viewModel::resetGame,
        backToMenuAction = backToMenuAction
    )

    BoxWithConstraints(
        Modifier.fillMaxSize().padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        val isVertical = maxHeight > maxWidth

        if (isVertical) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                BoardPane(modifier = Modifier.weight(1f), state = state, viewModel = viewModel)
                ActionsPane(modifier = Modifier, state = state, viewModel = viewModel)
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BoardPane(modifier = Modifier.weight(3f), state = state, viewModel = viewModel)
                ActionsPane(modifier = Modifier.weight(2f), state = state, viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun BoardPane(
    modifier: Modifier = Modifier,
    state: GameState,
    viewModel: GameViewModel
) {
    Column(modifier) {
        GameInformationRow(state)
        Spacer(modifier = Modifier.height(4.dp))
        SudokuField(state) { viewModel.setSelection(it) }
    }
}

@Composable
private fun ActionsPane(
    modifier: Modifier = Modifier,
    state: GameState,
    viewModel: GameViewModel
) {
    Column(modifier) {
        Spacer(modifier = Modifier.height(48.dp))
        GameActions(viewModel = viewModel)
        Spacer(modifier = Modifier.height(24.dp))
        NumberSelection(state.missingNumbers) {
            viewModel.setNumber(it)
        }
    }
}

@Composable
private fun GameStateDialogs(
    isGameOver: Boolean,
    isGameComplete: Boolean,
    mistakes: Int,
    resetGameAction: () -> Unit,
    backToMenuAction: () -> Unit
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
            confirmAction = backToMenuAction
        )
    }
}