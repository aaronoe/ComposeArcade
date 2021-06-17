package de.aaronoe.composegames.features.root

import androidx.compose.animation.Crossfade
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.*
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.asState
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import de.aaronoe.composegames.common.sudoku.SudokuComponent
import de.aaronoe.composegames.common.sudoku.model.GameDifficulty
import de.aaronoe.composegames.features.home.HomeComponent
import de.aaronoe.composegames.utils.Component

class NavHostComponent(
    componentContext: ComponentContext,
) : Component, ComponentContext by componentContext {

    private sealed class Config(val title: String) : Parcelable {
        @Parcelize
        object Home : Config("Home")

        @Parcelize
        data class SudokuGame(val difficulty: GameDifficulty) : Config("Sudoku")
    }

    private val router = router<Config, Component>(
        initialConfiguration = Config.Home,
        childFactory = ::createScreenComponent,
        handleBackButton = true
    )

    @OptIn(ExperimentalMaterialApi::class)
    private fun createScreenComponent(config: Config, componentContext: ComponentContext): Component {
        return when (config) {
            Config.Home -> HomeComponent(::navigateSudoku, componentContext)
            is Config.SudokuGame -> SudokuComponent(config.difficulty, componentContext)
        }
    }

    private fun navigateSudoku(difficulty: GameDifficulty) {
        router.push(Config.SudokuGame(difficulty))
    }

    @Composable
    override fun render() {
        val routerState by router.state.asState()

        Scaffold(
            topBar = { GameAppBar(routerState, router::pop) }
        ) {
            Children(
                routerState = router.state,
                animation = slide(),
            ) { child ->
                child.instance.render()
            }
        }
    }

    @Composable
    private fun GameAppBar(
        routerState: RouterState<Config, Component>,
        onBackClick: () -> Unit
    ) {
        val isRoot = routerState.backStack.isEmpty()
        val title = routerState.activeChild.configuration.title

        if (isRoot) {
            TopAppBar(
                title = { Crossfade(title) { Text(it) } },
            )
        } else {
            TopAppBar(
                title = { Crossfade(title) { Text(it) } },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    }
}