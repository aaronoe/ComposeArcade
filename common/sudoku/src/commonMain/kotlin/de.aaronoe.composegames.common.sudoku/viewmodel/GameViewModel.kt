package de.aaronoe.composegames.common.sudoku.viewmodel

import de.aaronoe.composegames.common.sudoku.logic.SudokuGameGenerator
import de.aaronoe.composegames.common.sudoku.model.*
import de.aaronoe.composegames.common.sudoku.utils.stopwatchFlow
import de.aaronoe.composegames.utils.mavericks.MavericksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GameViewModel(
    gameDifficulty: GameDifficulty
) : MavericksViewModel<GameState>(GameState()) {

    init {
        setState { copy(difficulty = gameDifficulty) }

        stopwatchFlow(updateInterval = 1000)
            .onEach {
                setState { copy(elapsedTime = it) }
            }
            .launchIn(viewModelScope)

        viewModelScope.launch(Dispatchers.Default) {
            val game = SudokuGameGenerator().generateBoard(gameDifficulty.missingCellCount)
            setState { copy(board = game) }
        }
    }

    fun setSelection(coordinates: CellCoordinates) = set {
        copy(selection = if (selection == coordinates) null else coordinates)
    }

    fun setNumber(number: Int) {
        setState {
            // no cell or a pre-filled cell is selected
            if (selection == null || board.getCell(selection).preset) {
                return@setState this
            }

            return@setState when (inputMode) {
                GameInputMode.Selection -> {
                    val cell = board.getCell(selection)
                    val isMistake = number != cell.selection && number != cell.correctValue

                    copy(
                        board = board.copyWithValue(selection, number),
                        mistakes = mistakes + if (isMistake) 1 else 0
                    )
                }
                GameInputMode.NoteTaking -> copy(board = board
                    .toggleNote(selection, number)
                    .copyWithValue(selection, null))
            }
        }
    }

    fun undoLastStep() {
        // TODO: find a good way to keep track of game state
    }

    fun eraseField() = setState {
        if (selection == null) {
            this
        } else {
            copy(board = board.copyWithValue(selection, null))
        }
    }

    fun toggleInputMode() = setState {
        copy(inputMode = if (inputMode == GameInputMode.Selection) GameInputMode.NoteTaking else GameInputMode.Selection)
    }

    fun resetGame() = setState {
        copy(
            board = board.map { row -> row.map { it.copy(selection = if (it.preset) it.selection else null) } },
            mistakes = 0
        )
    }

    fun fillHint() = setState {
        if (selection == null) {
            this
        } else {
            copy(board = board.updateCell(selection) { copy(selection = correctValue) })
        }
    }

}