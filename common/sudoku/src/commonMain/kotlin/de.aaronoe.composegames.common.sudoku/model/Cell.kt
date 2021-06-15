package de.aaronoe.composegames.common.sudoku.model

data class Cell(
    val row: Int,
    val col: Int,
    val correctValue: Int,
    val selection: Int? = null,
    val notes: Set<Int> = emptySet(),
    val preset: Boolean = false
) {
    val isMistake: Boolean
        get() = selection != null && selection != correctValue
}