package de.aaronoe.composegames.common.sudoku.logic

import de.aaronoe.composegames.common.sudoku.logic.Grid.Cell

/**
 * This class represents a Sudoku Grid consisting of a 9x9 matrix containing nine 3x3 sub-grids of
 * [Cell]s.
 */
class Grid private constructor(private val grid: Array<Array<Cell?>>) {
    /**
     * Returns the size of this Grid. This method is useful if you want to iterate over all [ ]s. <br></br><br></br> To access one cell use [.getCell]. <br></br><br></br> Note: This is the
     * size of one dimension. This Grid contains size x size [Cell]s.
     *
     * @return the size of this Grid
     */
    val size: Int
        get() = grid.size

    /**
     * Returns the [Cell] at the given position within the Grid. <br></br><br></br> This Grid has 0 to
     * [.getSize] rows and 0 to [.getSize] columns.
     *
     * @param row the row which contains the [Cell]
     * @param column the column which contains the [Cell]
     * @return the [Cell] at the given position
     */
    fun getCell(row: Int, column: Int): Cell? {
        return grid[row][column]
    }

    /**
     * Checks if a given value is valid for a certain [Cell]. <br></br><br></br> A value is valid if it
     * does not already exist in the same row, column and box.
     *
     * @param cell the [Cell] to check
     * @param value the value to validate
     * @return true if the given value is valid or false otherwise
     */
    fun isValidValueForCell(cell: Cell, value: Int): Boolean {
        return isValidInRow(cell, value) && isValidInColumn(cell, value) && isValidInBox(
            cell,
            value
        )
    }

    private fun isValidInRow(cell: Cell, value: Int): Boolean {
        return !getRowValuesOf(cell).contains(value)
    }

    private fun isValidInColumn(cell: Cell, value: Int): Boolean {
        return !getColumnValuesOf(cell).contains(value)
    }

    private fun isValidInBox(cell: Cell, value: Int): Boolean {
        return !getBoxValuesOf(cell).contains(value)
    }

    private fun getRowValuesOf(cell: Cell): Collection<Int> {
        val rowValues: MutableList<Int> = ArrayList()
        for (neighbor in cell.rowNeighbors!!) rowValues.add(neighbor.value)
        return rowValues
    }

    private fun getColumnValuesOf(cell: Cell): Collection<Int> {
        val columnValues: MutableList<Int> = ArrayList()
        for (neighbor in cell.columnNeighbors!!) columnValues.add(neighbor.value)
        return columnValues
    }

    private fun getBoxValuesOf(cell: Cell): Collection<Int> {
        val boxValues: MutableList<Int> = ArrayList()
        for (neighbor in cell.boxNeighbors!!) boxValues.add(neighbor.value)
        return boxValues
    }

    /**
     * Returns the first empty [Cell] of this Grid. <br></br><br></br> Note: The result is wrapped by an
     * [Optional].
     *
     * @return a non-null value containing the first empty [Cell] if present
     */
    val firstEmptyCell: Cell?
        get() {
            val firstCell = grid[0][0] ?: return null
            return if (firstCell.isEmpty) {
                firstCell
            } else getNextEmptyCellOf(firstCell)
        }

    /**
     * Returns the next empty [Cell] consecutively to the given [Cell] in this Grid.
     * <br></br><br></br> Note: The result is wrapped by an [Optional].
     *
     * @param cell the [Cell] of which the next empty [Cell] should be obtained
     * @return a non-null value containing the next empty [Cell] if present
     */
    fun getNextEmptyCellOf(cell: Cell?): Cell? {
        var cell = cell
        var nextEmptyCell: Cell? = null
        while (cell!!.nextCell.also { cell = it } != null) {
            if (!cell!!.isEmpty) {
                continue
            }
            nextEmptyCell = cell
            break
        }
        return nextEmptyCell
    }

    /**
     * Returns a [String] representation of this Grid.
     *
     * @return a [String] representation of this Grid.
     */
    override fun toString(): String {
        return StringConverter.toString(this)
    }

    /**
     * This class represents a Cell within a Sudoku [Grid]. <br></br><br></br> It features a couple of
     * convenient methods.
     */
    class Cell(
        /**
         * Allows to change the value of the Cell.
         *
         * @param value the new value of the Cell
         */
        var value: Int
    ) {
        /**
         * Returns the value of the Cell. <br></br><br></br> The value is a digit (1, ..., 9) or 0 if the Cell is
         * empty.
         *
         * @return the value of the Cell.
         */
        /**
         * Returns a [Collection] of all other Cells in the same row than this Cell.
         *
         * @return a [Collection] of row neighbors
         */
        /**
         * Allows to set a [Collection] of Cells, which are interpreted to be in the same row.
         *
         * @param rowNeighbors a [Collection] of row neighbors
         */
        var rowNeighbors: Collection<Cell>? = null
        /**
         * Returns a [Collection] of all other Cells in the same column than this Cell.
         *
         * @return a [Collection] of column neighbors
         */
        /**
         * Allows to set a [Collection] of Cells, which are interpreted to be in the same column.
         *
         * @param columnNeighbors a [Collection] of column neighbors
         */
        var columnNeighbors: Collection<Cell>? = null
        /**
         * Returns a [Collection] of all other Cells in the same box than this Cell.
         *
         * @return a [Collection] of box neighbors
         */
        /**
         * Allows to set a [Collection] of Cells, which are interpreted to be in the same box.
         *
         * @param boxNeighbors a [Collection] of box neighbors
         */
        var boxNeighbors: Collection<Cell>? = null
        /**
         * Returns the next Cell consecutive to this Cell. <br></br><br></br> This function returns the Cell to
         * the right of each Cell if the Cell is not the last Cell in a row. It returns the first Cell
         * of the next row of each Cell if the Cell is the last Cell in a row. For the very last Cell in
         * the very last row this function returns null.
         *
         * @return the next Cell consecutive to this Cell or null if it is the last Cell.
         */
        /**
         * Allows to set a Cell which is interpreted to be the next Cell consecutive to this Cell.
         *
         * @param nextCell the next Cell consecutive to this Cell.
         */
        var nextCell: Cell? = null

        /**
         * Indicates whether the Cell is empty or not.
         *
         * @return true if the Cell is empty, false otherwise
         */
        val isEmpty: Boolean
            get() = value == 0
    }

    private object StringConverter {
        fun toString(grid: Grid): String {
            val builder = StringBuilder()
            val size = grid.size
            printTopBorder(builder)
            for (row in 0 until size) {
                printRowBorder(builder)
                for (column in 0 until size) {
                    printValue(builder, grid, row, column)
                    printRightColumnBorder(builder, column + 1, size)
                }
                printRowBorder(builder)
                builder.append("\n")
                printBottomRowBorder(builder, row + 1, size)
            }
            printBottomBorder(builder)
            return builder.toString()
        }

        private fun printTopBorder(builder: StringBuilder) {
            builder.append("╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗\n")
        }

        private fun printRowBorder(builder: StringBuilder) {
            builder.append("║")
        }

        private fun printValue(builder: StringBuilder, grid: Grid, row: Int, column: Int) {
            val value = grid.getCell(row, column)!!.value
            val output = if (value != 0) value.toString() else " "
            builder.append(" $output ")
        }

        private fun printRightColumnBorder(builder: StringBuilder, column: Int, size: Int) {
            if (column == size) {
                return
            }
            if (column % 3 == 0) {
                builder.append("║")
            } else {
                builder.append("│")
            }
        }

        private fun printBottomRowBorder(builder: StringBuilder, row: Int, size: Int) {
            if (row == size) {
                return
            }
            if (row % 3 == 0) {
                builder.append("╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣\n")
            } else {
                builder.append("╟───┼───┼───╫───┼───┼───╫───┼───┼───╢\n")
            }
        }

        private fun printBottomBorder(builder: StringBuilder) {
            builder.append("╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝\n")
        }
    }

    companion object {
        /**
         * A static factory method which returns a Grid of a given two-dimensional array of integers.
         *
         * @param grid a two-dimensional int-array representation of a Grid
         * @return a Grid instance corresponding to the provided two-dimensional int-array
         */
        fun of(grid: Array<IntArray>): Grid {
            verifyGrid(grid)
            val cells = Array(9) { arrayOfNulls<Cell>(9) }
            val rows: MutableList<MutableList<Cell>> = ArrayList()
            val columns: MutableList<MutableList<Cell>> = ArrayList()
            val boxes: MutableList<MutableList<Cell>> = ArrayList()
            for (i in 0..8) {
                rows.add(ArrayList())
                columns.add(ArrayList())
                boxes.add(ArrayList())
            }
            var lastCell: Cell? = null
            for (row in grid.indices) {
                for (column in 0 until grid[row].size) {
                    val cell = Cell(grid[row][column])
                    cells[row][column] = cell
                    rows[row].add(cell)
                    columns[column].add(cell)
                    boxes[row / 3 * 3 + column / 3].add(cell)
                    if (lastCell != null) {
                        lastCell.nextCell = cell
                    }
                    lastCell = cell
                }
            }
            for (i in 0..8) {
                val row: List<Cell> = rows[i]
                for (cell in row) {
                    val rowNeighbors: MutableList<Cell> = ArrayList(row)
                    rowNeighbors.remove(cell)
                    cell.rowNeighbors = rowNeighbors
                }
                val column: List<Cell> = columns[i]
                for (cell in column) {
                    val columnNeighbors: MutableList<Cell> = ArrayList(column)
                    columnNeighbors.remove(cell)
                    cell.columnNeighbors = columnNeighbors
                }
                val box: List<Cell> = boxes[i]
                for (cell in box) {
                    val boxNeighbors: MutableList<Cell> = ArrayList(box)
                    boxNeighbors.remove(cell)
                    cell.boxNeighbors = boxNeighbors
                }
            }
            return Grid(cells)
        }

        /**
         * A static factory method which returns an empty Grid.
         *
         * @return an empty Grid
         */
        fun emptyGrid(): Grid {
            val emptyGrid = Array(9) { IntArray(9) }
            return of(emptyGrid)
        }

        private fun verifyGrid(grid: Array<IntArray>?) {
            requireNotNull(grid) { "grid must not be null" }
            require(grid.size == 9) { "grid must have nine rows" }
            for (row in grid) {
                require(row.size == 9) { "grid must have nine columns" }
                for (value in row) {
                    require(!(value < 0 || value > 9)) { "grid must contain values from 0-9" }
                }
            }
        }
    }
}