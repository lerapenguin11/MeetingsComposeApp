package com.example.composeprotject.container

import android.annotation.SuppressLint
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.nav.Main
import com.example.composeprotject.nav.MainGraph
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.state.TopBarState
import com.example.composeprotject.ui.component.topBar.search.SearchBar
import com.example.composeprotject.ui.component.topBar.standard.TopAppBar
import com.example.composeprotject.viewModel.SearchViewModel
import org.koin.androidx.compose.navigation.koinNavViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun MainContainer() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val baseRoute = currentRoute?.substringBefore("/") ?: ""
    val topBarIsShow = rememberSaveable { (mutableStateOf(TopBarState.MAIN_TOP_BAR)) }
    val searchViewModel: SearchViewModel = koinNavViewModel()
    val authToken by searchViewModel.getAuthToken().collectAsStateWithLifecycle()

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
                        authToken = authToken,
                        state = InputState.SUCCESS,
                        onValueChange = {
                            searchViewModel.searchQueryUpdate(text = it)
                        },
                        onMainScreenState = {
                            searchViewModel.mainScreenStateUpdate(state = it)
                        },
                        onGoProfile = {
                            //TODO
                            navController.navigate(route = Graph.PROFILE) {

                            }
                        }
                    )
                }

                TopBarState.DETAILS_TOP_BAR -> {
                    TopAppBar(navController = navController)
                }

                TopBarState.NO_TOP_BAR -> {}

                TopBarState.PROFILE_TOP_BAR -> TODO()
            }
        }
    ) { contentPadding ->
        MainGraph(
            navController = navController,
            contentPadding = contentPadding,
            searchViewModel = searchViewModel
        )
    }
}