package de.aaronoe.composegames.common.sudoku.logic

import de.aaronoe.composegames.common.sudoku.model.Cell

class SudokuGameGenerator {

    fun generateBoard(emptyCells: Int = 42): List<List<Cell>> {
        val generator = Generator()
        val grid = generator.generateFullSolution()

        val solution = List(9) { row ->
            List(9) { col ->
                grid.getCell(row, col)!!.value
            }
        }

        generator.eraseCells(grid, emptyCells)

        return List(9) { row ->
            List(9) { col ->
                val value = grid.getCell(row, col)!!.value.let { if (it == 0) null else it }
                Cell(row, col,
                    selection = value,
                    preset = value != null,
                    correctValue = solution[row][col]
                )
            }
        }
    }

}