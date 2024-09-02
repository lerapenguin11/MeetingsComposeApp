package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composeprotject.container.MainContainer
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.nav_old.navigateToSingleTop
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
        /*route = Graph.ROOT,*/
        startDestination = Graph.ROOT
    ) {
        splash(contentPadding = contentPadding)
        onBoardingNavGraph(navController = navHostController, contentPadding = contentPadding)
        /*splash(contentPadding = contentPadding)
        //onBoardingNavGraph(navHostController)
        */
        composable(route = Graph.MAIN) {
            MainContainer()
        }
    }
    LaunchedEffect(isLoading) {
        navHostController.navigateToSingleTop(Graph.ON_BOARDING)
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