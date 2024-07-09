package com.example.composeprotject

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.navigation.BottomNavItem
import com.example.composeprotject.navigation.NavigationHost
import com.example.composeprotject.navigation.navComponent.BottomNavigationBar
import com.example.composeprotject.ui.component.toolbar.ActionMode
import com.example.composeprotject.ui.component.toolbar.BackNavigationMode
import com.example.composeprotject.ui.component.toolbar.CustomToolbar
import com.example.composeprotject.ui.component.toolbar.ToolbarTitle
import com.example.composeprotject.ui.component.toolbar.ToolbarTitleMode
import com.example.composeprotject.ui.component.toolbar.getActionToolbar
import com.example.composeprotject.ui.component.toolbar.getBackNavigation
import com.example.composeprotject.ui.component.toolbar.getToolbarTitle
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.SplashScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun AppContainer() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val splashScreenViewModel: SplashScreenViewModel = viewModel()
    val currentScreen by viewModel.currentScreen.observeAsState()
    val showTopBar by viewModel.showTopBar.observeAsState()
    val showBottomBar by viewModel.showBottomBar.observeAsState()

    val topBar: @Composable () -> Unit = {
        CustomToolbar(
            navigationIcon = getNavigationIconSlot(currentScreen),
            actions = getActionsSlot(currentScreen),
            toolbarTitle = ToolbarTitle(
                titleText = getToolbarTitleSlot(currentScreen),
                expandedTextStyle = MeetTheme.typography.subheading1,
                titleColor = MeetTheme.colors.neutralActive
            )
        )
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MeetTheme.colors.neutralWhite,
        topBar = {
            if (showTopBar!!){
                topBar()
            }
        },
        bottomBar = {
            if (showBottomBar!!){
                BottomNavigationBar(navController)
            }
        }
    ) { contentPadding ->
        NavigationHost(
            navController = navController,
            contentPadding = contentPadding,
            mainViewModel = viewModel,
            splashScreenViewModel = splashScreenViewModel
        )
    }
}

@Composable
private fun getToolbarTitleSlot(currentScreen: BottomNavItem?): String? {
    val mode = getToolbarTitle(currentScreen!!.route)
    return when (mode) {
        ToolbarTitleMode.TITLE -> stringResource(id = currentScreen.name)
        ToolbarTitleMode.NONE -> null
    }
}

private fun getNavigationIconSlot(currentScreen: BottomNavItem?): (@Composable () -> Unit)? {
    val mode = getBackNavigation(currentScreen!!.route)
    return when (mode) {
        BackNavigationMode.BACK_ARROW -> {
            { Icon(painterResource(id = R.drawable.ic_arrow_back), null) }
        }

        BackNavigationMode.NONE -> {
            null
        }
    }
}

private fun getActionsSlot(currentScreen: BottomNavItem?): (@Composable RowScope.() -> Unit)? {
    val mode = getActionToolbar(currentScreen!!.route)
    return when (mode) {
        ActionMode.ADD_ICON -> {
            {
                Icon(painterResource(id = R.drawable.ic_add_event), null)
            }
        }

        ActionMode.EDIT_ICON -> {
            {
                Icon(painterResource(id = R.drawable.ic_edit_profile), null)
            }
        }

        ActionMode.NONE -> {
            null
        }
    }
}