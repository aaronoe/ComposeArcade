package de.aaronoe.composegames.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import de.aaronoe.composegames.common.sudoku.model.GameDifficulty
import de.aaronoe.composegames.common.sudoku.utils.SudokuColors
import de.aaronoe.composegames.utils.Component
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class HomeComponent(
    private val onClickSudoku: (GameDifficulty) -> Unit,
    componentContext: ComponentContext,
) : Component, ComponentContext by componentContext {

    @Composable
    override fun render() {
        val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        ModalBottomSheetLayout(
            sheetContent = {
                NewGameBottomSheet {
                    scope.launch { state.hide() }
                    onClickSudoku(it)
                }
            },
            sheetState = state
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sudoku",
                    style = MaterialTheme.typography.h3.copy(color = SudokuColors.NumberSelectionColor)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(onClick = { scope.launch { state.show() } }) {
                    Text(text = "New Game")
                }
            }
        }
    }


    @Composable
    private fun NewGameBottomSheet(onSelected: (GameDifficulty) -> Unit) {
        Column {
            GameDifficultySelectionCell(label = "Easy", icon = Icons.Default.Info) {
                onSelected(GameDifficulty.Easy)
            }
            GameDifficultySelectionCell(label = "Medium", icon = Icons.Filled.Info) {
                onSelected(GameDifficulty.Medium)
            }
            GameDifficultySelectionCell(label = "Hard", icon = Icons.Filled.Info) {
                onSelected(GameDifficulty.Hard)
            }
            GameDifficultySelectionCell(label = "Random", icon = Icons.Filled.Info) {
                onSelected(GameDifficulty.Random)
            }
        }
    }

    @Composable
    private fun GameDifficultySelectionCell(
        label: String,
        icon: ImageVector,
        onSelected: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelected() }
                .padding(16.dp)
        ) {
            Icon(icon, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label)
        }
    }
}