package de.aaronoe.composegames.utils.mavericks

import kotlinx.coroutines.flow.Flow

interface MavericksStateStore<S : Any> {
    val state: S
    val flow: Flow<S>
    fun get(block: (S) -> Unit)
    fun set(stateReducer: S.() -> S)
}
