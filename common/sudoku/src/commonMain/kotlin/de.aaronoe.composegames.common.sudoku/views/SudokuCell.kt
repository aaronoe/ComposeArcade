package de.aaronoe.composegames.common.sudoku.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.aaronoe.composegames.common.sudoku.model.Cell
import de.aaronoe.composegames.common.sudoku.model.CellSelectionState
import de.aaronoe.composegames.common.sudoku.utils.Border
import de.aaronoe.composegames.common.sudoku.utils.SudokuColors
import de.aaronoe.composegames.common.sudoku.utils.border

private val border = Border(strokeWidth = 1.dp, Color.Gray)
private val thickBorder = border.copy(strokeWidth = 2.dp)

@Composable
fun SudokuCell(
    cell: Cell,
    selectionState: CellSelectionState,
    onClick: () -> Unit
) {
    val (row, col) = cell

    val color = selectionState.color

    BoxWithConstraints(modifier = Modifier
        .background(color = color)
        .border(
            end = if (col == 8) thickBorder else border,
            bottom = if (row == 8) thickBorder else border,
            start = if (col % 3 == 0) thickBorder else null,
            top = if (row % 3 == 0) thickBorder else null
        )
        .fillMaxSize()
        .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (cell.selection != null) {
            Text(
                text = cell.selection.toString(),
                fontSize = MaterialTheme.typography.h5.fontSize,
                color = if (cell.preset) Color.Black else SudokuColors.NumberSelectionColor
            )
        } else if (cell.notes.isNotEmpty()) {
            CellNotesGrid(containerSize = maxWidth, notes = cell.notes)
        }
    }
}

@Composable
private fun CellNotesGrid(containerSize: Dp, notes: Set<Int>) {
    val size = containerSize / 3

    Row {
        repeat(3) { row ->
            Column {
                repeat(3) { col ->
                    val number = row * 3 + col + 1

                    Text(
                        text = if (number in notes) number.toString() else "",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.size(size),
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}