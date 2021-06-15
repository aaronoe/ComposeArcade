package de.aaronoe.composegames.utils.mavericks

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

abstract class MavericksViewModel<T: MavericksState>(
    initialState: T,
    private val job: Job = SupervisorJob(),
    protected val viewModelScope: CoroutineScope = CoroutineScope(job)
): MavericksStateStore<T> by CoroutinesStateStore(initialState, viewModelScope)  {

    fun setState(stateReducer: T.() -> T) = set(stateReducer)

    fun dispose() {
        job.cancel()
    }

}