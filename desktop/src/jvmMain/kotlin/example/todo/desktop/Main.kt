package example.todo.desktop

import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import de.aaronoe.composegames.features.root.NavHostComponent

fun main() {
    Window("Compose Arcade") {
        Surface(modifier = Modifier.fillMaxSize()) {
            DesktopMaterialTheme {
                rememberRootComponent(factory = ::NavHostComponent)
                    .render()
            }
        }
    }
}

