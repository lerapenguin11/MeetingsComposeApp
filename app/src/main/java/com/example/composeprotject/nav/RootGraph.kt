package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composeprotject.container.MainContainer
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.screen.splash.SplashScreen

@Composable
fun RootNavigationGraph(
    contentPadding: PaddingValues,
    navHostController: NavHostController,
    startDestination: String,
    isLoading: Boolean
) {
    NavHost(
        navController = navHostController,
        startDestination = Graph.ROOT
    ) {
        splash(contentPadding = contentPadding)
        onBoardingNavGraph(navController = navHostController, contentPadding = contentPadding)
        locationGraph(navController = navHostController, contentPadding = contentPadding)
        composable(route = Graph.MAIN) {
            MainContainer()
        }
    }
    LaunchedEffect(startDestination) {
        when (startDestination) {
            Graph.MAIN -> navHostController.navigateToSingleTop(Graph.MAIN)
            Graph.LOCATION -> navHostController.navigateToSingleTop(Graph.LOCATION)
        }
    }
}

fun NavGraphBuilder.splash(contentPadding: PaddingValues) {
    navigation(startDestination = SplashScreen.Splash.route, route = Graph.ROOT) {
        composable(route = SplashScreen.Splash.route) {
            SplashScreen(
                contentPadding = contentPadding
            )
        }
    }
}

sealed class SplashScreen(val route: String) {
    object Splash : SplashScreen(route = "splash_screen")
}

fun NavController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
}