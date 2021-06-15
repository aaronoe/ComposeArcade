package de.aaronoe.composegames.common.sudoku.model

typealias Board = List<List<Cell>>

fun Board.getCell(index: CellCoordinates): Cell {
    val (row, col) = index

    return this[row][col]
}

fun Board.copyWithValue(coordinates: CellCoordinates, value: Int?): Board =
    updateCell(coordinates) { copy(selection = value) }

fun Board.toggleNote(coordinates: CellCoordinates, value: Int): Board =
    updateCell(coordinates) {
        val noteCopy = this.notes.toMutableSet()

        if (value in noteCopy) {
            noteCopy -= value
        } else {
            noteCopy += value
        }

        copy(notes = noteCopy)
    }

fun Board.updateCell(
    coordinates: CellCoordinates,
    updater: Cell.() -> Cell
): Board {
    return this.toMutableList().mapIndexed { index, list ->
        val inner = list.toMutableList()

        if (coordinates.first == index) {
            inner[coordinates.second] = updater(inner[coordinates.second])
        }

        inner
    }
}