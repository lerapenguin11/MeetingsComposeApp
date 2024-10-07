package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.screen.onboarding.LocationScreen

fun NavGraphBuilder.locationGraph(
    navController: NavController,
    contentPadding: PaddingValues
) {
    navigation(
        route = Graph.LOCATION,
        startDestination = Location.Loc.route
    ) {
        composable(route = Location.Loc.route) {
            LocationScreen(contentPadding = contentPadding) {
                navController.navigate(
                    when (it) {
                        Graph.MAIN -> Graph.MAIN
                        Graph.ON_BOARDING -> Graph.ON_BOARDING
                        else -> {
                            Graph.MAIN
                        }
                    }
                ) {
                    popUpTo(Graph.LOCATION) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

sealed class Location(val route: String) {
    object Loc : Location(route = "location_screen")
}