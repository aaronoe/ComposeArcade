package de.aaronoe.composegames.common.sudoku.model

import kotlin.random.Random

enum class GameDifficulty {
    Easy,
    Medium,
    Hard,
    Random
}

val GameDifficulty.missingCellCount: Int
    get() {
        return when (this) {
            GameDifficulty.Easy -> Random.nextInt(20, 30)
            GameDifficulty.Medium -> Random.nextInt(30, 40)
            GameDifficulty.Hard -> Random.nextInt(45, 55)
            GameDifficulty.Random -> Random.nextInt(20, 55)
        }
    }