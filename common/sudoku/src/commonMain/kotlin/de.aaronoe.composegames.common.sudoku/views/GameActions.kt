package de.aaronoe.composegames.common.sudoku.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.aaronoe.composegames.common.sudoku.utils.SudokuColors
import de.aaronoe.composegames.common.sudoku.viewmodel.GameInputMode
import de.aaronoe.composegames.common.sudoku.viewmodel.GameState
import de.aaronoe.composegames.common.sudoku.viewmodel.GameViewModel
import de.aaronoe.composegames.utils.mavericks.collectAsState

@Composable
fun GameActions(viewModel: GameViewModel) {
    val inputMode by viewModel.collectAsState(GameState::inputMode)
    val isTakingNotes by derivedStateOf { inputMode == GameInputMode.NoteTaking }

    CompositionLocalProvider(LocalContentColor provides SudokuColors.GameActionsColor) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GameActionButton(text = "Undo", icon = Icons.Filled.ArrowBack) {
                viewModel.undoLastStep()
            }

            GameActionButton(text = "Erase", Icons.Filled.Delete) {
                viewModel.eraseField()
            }

            IconToggleButton(
                checked = isTakingNotes,
                onCheckedChange = { viewModel.toggleInputMode() }
            ) {
                val color =
                    if (isTakingNotes) SudokuColors.NumberSelectionColor else SudokuColors.GameActionsColor
                val animatedColor by animateColorAsState(targetValue = color)

                CompositionLocalProvider(LocalContentColor provides animatedColor) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Info, contentDescription = "Notes")
                        Text(text = "Notes")
                    }
                }
            }

            GameActionButton(text = "Hint", icon = Icons.Filled.CheckCircle) {
                viewModel.fillHint()
            }
        }
    }
}