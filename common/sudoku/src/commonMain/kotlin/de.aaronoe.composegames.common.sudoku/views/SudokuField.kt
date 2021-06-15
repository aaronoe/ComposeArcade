package de.aaronoe.composegames.common.sudoku.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.min
import de.aaronoe.composegames.common.sudoku.model.Board
import de.aaronoe.composegames.common.sudoku.model.CellCoordinates
import de.aaronoe.composegames.common.sudoku.model.CellSelectionState
import de.aaronoe.composegames.common.sudoku.model.getCell
import de.aaronoe.composegames.common.sudoku.viewmodel.GameState
import de.aaronoe.composegames.common.sudoku.viewmodel.GameViewModel
import de.aaronoe.composegames.utils.mavericks.collectAsState

private const val CellCount = 9

@Composable
fun SudokuField(viewModel: GameViewModel) {
    val selection by viewModel.collectAsState(GameState::selection)
    val board by viewModel.collectAsState(GameState::board)

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val size = min(maxWidth, maxHeight)

        Column(Modifier.size(size = size)) {
            repeat(CellCount) { row ->
                Row(Modifier.fillMaxWidth()) {
                    repeat(CellCount) { col ->
                        Box(modifier = Modifier.size(size / CellCount)) {
                            val coordinates: CellCoordinates = row to col
                            val state = getSelectionState(board, selection, coordinates)

                            SudokuCell(
                                cell = board.getCell(coordinates),
                                selectionState = state,
                                onClick = {
                                    viewModel.setSelection(coordinates)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getSelectionState(
    board: Board,
    selection: CellCoordinates?,
    cell: CellCoordinates
): CellSelectionState {
    return when {
        board.getCell(cell).isMistake -> CellSelectionState.Mistake
        selection == null -> CellSelectionState.None
        selection == cell -> CellSelectionState.Selected
        selection.first == cell.first -> CellSelectionState.RelativeSelected
        selection.second == cell.second -> CellSelectionState.RelativeSelected
        selection.first / 3 == cell.first / 3 && selection.second / 3 == cell.second / 3 -> {
            CellSelectionState.RelativeSelected
        }
        board.getCell(selection).selection != null
                && board.getCell(selection).selection == board.getCell(cell).selection -> {
            CellSelectionState.SameNumber
        }
        else -> CellSelectionState.None
    }

}
