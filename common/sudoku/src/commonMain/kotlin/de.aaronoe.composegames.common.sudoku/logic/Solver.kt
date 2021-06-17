// Copyright (c) 2015 André Diermann

package de.aaronoe.composegames.common.sudoku.logic

import kotlin.random.Random

/**
 * A Solver is capable of solving a given Sudoku [Grid].
 */
class Solver {
    private val values: IntArray

    /**
     * Solves a given [Grid] using backtracking.
     *
     * @param grid the [Grid] to solve
     * @throws IllegalStateException in case the provided [Grid] is invalid.
     */
    fun solve(grid: Grid) {
        val solvable = solve(grid, grid.firstEmptyCell)
        check(solvable) { "The provided grid is not solvable." }
    }

    private fun solve(grid: Grid, cell: Grid.Cell?): Boolean {
        if (cell == null) {
            return true
        }
        for (value in values) {
            if (grid.isValidValueForCell(cell, value)) {
                cell.value = value
                if (solve(grid, grid.getNextEmptyCellOf(cell))) return true
                cell.value = EMPTY
            }
        }
        return false
    }

    private fun generateRandomValues(): IntArray {
        val values = intArrayOf(EMPTY, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        var i = 0
        var j = Random.nextInt(9)
        var tmp = values[j]
        while (i < values.size) {
            if (i == j) {
                i++
                j = Random.nextInt(9)
                tmp = values[j]
                continue
            }
            values[j] = values[i]
            values[i] = tmp
            i++
            j = Random.nextInt(9)
            tmp = values[j]
        }
        return values
    }

    companion object {
        private const val EMPTY = 0
    }

    /**
     * Constructs a new Solver instance.
     */
    init {
        values = generateRandomValues()
    }
}