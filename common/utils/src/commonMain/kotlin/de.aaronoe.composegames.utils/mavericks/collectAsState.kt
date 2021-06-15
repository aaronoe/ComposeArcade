package de.aaronoe.composegames.utils.mavericks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.reflect.KProperty1

/**
 * Creates a Compose State variable that will emit new values whenever this ViewModel's state changes.
 * Prefer the overload with a state property reference to ensure that your composable only recomposes when the properties it uses changes.
 */
@Composable
fun <VM : MavericksViewModel<S>, S : MavericksState> VM.collectAsState(): State<S> {
    return flow.collectAsState(initial = state)
}

/**
 * Creates a Compose State variable that will emit new values whenever this ViewModel's state mapped to the provided mapper changes.
 * Prefer the overload with a state property reference to ensure that your composable only recomposes when the properties it uses changes.
 */
@Composable
fun <VM : MavericksViewModel<S>, S : MavericksState, O> VM.collectAsState(mapper: (S) -> O): State<O> {
    return flow.map { mapper(it) }.distinctUntilChanged().collectAsState(initial = mapper(state))
}

/**
 * Creates a Compose State variable that will only update when the value of this property changes.
 * Prefer this to subscribing to entire state classes which will trigger a recomposition whenever any state variable changes.
 * If you find yourself subscribing to many state properties in a single composable, consider breaking it up into smaller ones.
 */
@Composable
fun <VM : MavericksViewModel<S>, S : MavericksState, A> VM.collectAsState(prop1: KProperty1<S, A>): State<A> {
    return flow.map { prop1.get(it) }.distinctUntilChanged().collectAsState(initial = prop1.get(state))
}