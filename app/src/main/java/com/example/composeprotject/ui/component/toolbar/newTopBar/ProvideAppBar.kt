package com.example.composeprotject.ui.component.toolbar.newTopBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.LocalOwnersProvider

@Composable
fun ProvideAppBarAction(actions: @Composable RowScope.() -> Unit) {
    if (LocalViewModelStoreOwner.current == null || LocalViewModelStoreOwner.current !is NavBackStackEntry)
        return
    val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
    SideEffect {
        actionViewModel.actionState = actions
    }
}

@Composable
fun ProvideAppBarTitle(title: @Composable () -> Unit) {
    if (LocalViewModelStoreOwner.current == null || LocalViewModelStoreOwner.current !is NavBackStackEntry)
        return
    val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
    SideEffect {
        actionViewModel.titleState = title
    }
}

@Composable
fun RowScope.AppBarAction(navBackStackEntry: NavBackStackEntry?) {
    val stateHolder = rememberSaveableStateHolder()
    navBackStackEntry?.LocalOwnersProvider(stateHolder) {
        val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
        actionViewModel.actionState?.let { it() }
    }
}


@Composable
fun AppBarTitle(navBackStackEntry: NavBackStackEntry?) {
    val stateHolder = rememberSaveableStateHolder()
    navBackStackEntry?.LocalOwnersProvider(stateHolder) {
        val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
        actionViewModel.titleState?.let { it() }
    }
}


private class TopAppBarViewModel : ViewModel() {

    var titleState by mutableStateOf(null as (@Composable () -> Unit)?, referentialEqualityPolicy())
    var actionState by mutableStateOf(null as (@Composable RowScope.() -> Unit)?, referentialEqualityPolicy())

}