package de.aaronoe.composegames.common.sudoku.model

import androidx.compose.ui.graphics.Color
import de.aaronoe.composegames.common.sudoku.utils.SudokuColors

enum class CellSelectionState(val color: Color) {
    None(SudokuColors.cellColor),
    RelativeSelected(SudokuColors.selectedRelativeColor),
    Selected(SudokuColors.selectedCellColor),
    SameNumber(SudokuColors.sameNumberColor),
    Mistake(Color.Red)
}
