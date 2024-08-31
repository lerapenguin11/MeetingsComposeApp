package com.example.composeprotject.nav_old

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
import com.example.composeprotject.EventContainer
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.screen_old.splashScreen.SplashScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    isLoggedIn: Boolean,
    contentPadding: PaddingValues
) {

    NavHost(navController, startDestination = Graph.ROOT) {
        splash(contentPadding = contentPadding)
        auth(navController = navController, contentPadding = contentPadding)
        composable(route = Graph.EVENT) {
            EventContainer()
        }
    }
    LaunchedEffect(Unit) {
        if (!isLoggedIn) {
            navController.navigateToSingleTop(Graph.AUTHENTICATION)
        } else {
            navController.navigateToSingleTop(Graph.EVENT)
        }
    }
}

fun NavGraphBuilder.splash(contentPadding: PaddingValues) {
    navigation(startDestination = NavItem.SplashScreenItem.route, route = Graph.ROOT) {
        composable(route = NavItem.SplashScreenItem.route) {
            SplashScreen(
                contentPadding = contentPadding
            )
        }
    }
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