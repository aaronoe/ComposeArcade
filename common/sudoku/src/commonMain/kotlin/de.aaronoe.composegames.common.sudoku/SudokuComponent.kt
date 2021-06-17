package de.aaronoe.composegames.common.sudoku

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import de.aaronoe.composegames.common.sudoku.model.GameDifficulty
import de.aaronoe.composegames.common.sudoku.viewmodel.GameViewModel
import de.aaronoe.composegames.common.sudoku.views.SudokuGame
import de.aaronoe.composegames.utils.Component
import de.aaronoe.composegames.utils.extensions.getViewModel

class SudokuComponent(
    private val gameDifficulty: GameDifficulty,
    private val componentContext: ComponentContext,
    private val backToMenuAction: () -> Unit
) : Component, ComponentContext by componentContext {

    private val viewModel = getViewModel { GameViewModel(gameDifficulty) }

    @Composable
    override fun render() {
        SudokuGame(viewModel = viewModel, backToMenuAction = backToMenuAction)
    }

}