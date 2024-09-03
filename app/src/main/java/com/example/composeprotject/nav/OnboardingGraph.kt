package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.screen.onboarding.InterestsScreen

fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavController,
    contentPadding: PaddingValues
) {
    navigation(
        route = Graph.ON_BOARDING,
        startDestination = OnBoardingScreen.Interests.route
    ) {
        composable(route = OnBoardingScreen.Interests.route) {
            InterestsScreen(contentPadding = contentPadding) {
                navController.navigate(Graph.MAIN) {
                    popUpTo(Graph.ON_BOARDING) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

sealed class OnBoardingScreen(val route: String) {
    object Interests : OnBoardingScreen("interests_screen")
}