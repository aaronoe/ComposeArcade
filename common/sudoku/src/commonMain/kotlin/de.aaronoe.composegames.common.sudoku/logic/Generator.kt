package de.aaronoe.composegames.common.sudoku.logic

import kotlin.random.Random

/**
 * A Generator to generate random Sudoku [Grid] instances.
 */
class Generator {
    private val solver: Solver = Solver()

    /**
     * Generates a random [Grid] instance with the given number of empty [Grid.Cell]s.
     * <br></br><br></br>
     * Note: The complexity for a human player increases with an higher amount of empty [Grid.Cell]s.
     * @param numberOfEmptyCells the number of empty [Grid.Cell]s
     * @return a randomly filled Sudoku [Grid] with the given number of empty [Grid.Cell]s
     */
    fun generate(numberOfEmptyCells: Int): Grid {
        val grid = generate()
        eraseCells(grid, numberOfEmptyCells)
        return grid
    }

    fun generateFullSolution(): Grid {
        return generate()
    }

    fun eraseCells(grid: Grid, numberOfEmptyCells: Int) {
        var i = 0
        while (i < numberOfEmptyCells) {
            val randomRow = Random.nextInt(9)
            val randomColumn = Random.nextInt(9)
            val cell = grid.getCell(randomRow, randomColumn)
            if (!cell!!.isEmpty) {
                cell.value = 0
            } else {
                i--
            }
            i++
        }
    }

    private fun generate(): Grid {
        val grid = Grid.emptyGrid()
        solver.solve(grid)
        return grid
    }

}