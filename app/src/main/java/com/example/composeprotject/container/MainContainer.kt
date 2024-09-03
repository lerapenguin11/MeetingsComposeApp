package com.example.composeprotject.container

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.nav.Main
import com.example.composeprotject.nav.MainGraph
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.state.TopBarState
import com.example.composeprotject.ui.component.topBar.TopAppBar
import com.example.composeprotject.ui.component.topBar.searchTopBar.SearchBar

@Composable
fun MainContainer() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val baseRoute = currentRoute?.substringBefore("/") ?: ""
    val topBarIsShow = rememberSaveable { (mutableStateOf(TopBarState.MAIN_TOP_BAR)) }

    topBarIsShow.value = when (baseRoute) {
        Main.CommunityDetails.route,
        Main.EventDetails.route,
        Main.PeopleEvent.route,
        Main.PeopleCommunity.route -> TopBarState.DETAILS_TOP_BAR

        Main.Home.route -> TopBarState.MAIN_TOP_BAR
        else -> TopBarState.NO_TOP_BAR
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = Color.White,
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            when (topBarIsShow.value) {
                TopBarState.MAIN_TOP_BAR -> {
                    SearchBar(
                        isEnabled = true,
                        state = InputState.SUCCESS,
                        onValueChange = {
                            /*TODO*/
                        }
                    )
                }

                TopBarState.DETAILS_TOP_BAR -> {
                    TopAppBar(navController = navController)
                }

                TopBarState.NO_TOP_BAR -> {}
            }
        }
    ) { contentPadding ->
        MainGraph(
            navController = navController,
            contentPadding = contentPadding
        )
    }
}