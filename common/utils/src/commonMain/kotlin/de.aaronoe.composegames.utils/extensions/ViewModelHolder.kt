package de.aaronoe.composegames.utils.extensions

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.instancekeeper.InstanceKeeper
import com.arkivanov.decompose.instancekeeper.getOrCreate
import de.aaronoe.composegames.utils.mavericks.MavericksViewModel

private class ViewModelHolder<VM: MavericksViewModel<*>>(
    val viewModel: VM
): InstanceKeeper.Instance {
    override fun onDestroy() {
        viewModel.dispose()
    }
}

fun <VM: MavericksViewModel<*>> ComponentContext.getViewModel(factory: () -> VM): VM {
    return instanceKeeper.getOrCreate { ViewModelHolder(factory()) }.viewModel
}