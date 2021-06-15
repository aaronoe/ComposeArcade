package de.aaronoe.composegames.common.sudoku.utils

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.isActive

fun stopwatchFlow(updateInterval: Long): Flow<Long> {
    return flow {
        while (currentCoroutineContext().isActive) {
            delay(updateInterval)
            emit(updateInterval)
        }
    }
        .runningFold(0L) { accumulator, value -> accumulator + value }
        .cancellable()
}