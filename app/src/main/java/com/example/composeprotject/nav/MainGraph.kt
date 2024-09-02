package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.screen.details.CommunityDetailsScreen
import com.example.composeprotject.screen.details.EventDetailsScreen
import com.example.composeprotject.screen.main.MainScreen
import com.example.composeprotject.screen.people.PeopleScreen

@Composable
fun MainGraph(navController: NavHostController, contentPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Main.Home.route,
        route = Graph.MAIN
    ) {
        composable(route = Main.Home.route) {
            MainScreen(
                contentPadding = contentPadding,
                onClickEvent = { meeting ->
                    navController.navigate(
                        route = "${Main.EventDetails.route}/${meeting.id}"
                    )
                },
                onClickCommunity = { community ->
                    navController.navigate(
                        route = "${Main.CommunityDetails.route}/${community.id}"
                    )
                }
            )
        }
        composable(
            route = "${Main.EventDetails.route}/{$EVENT_ID}",
            arguments = listOf(
                navArgument(EVENT_ID) { type = NavType.IntType },
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(EVENT_ID)?.let {
                EventDetailsScreen(
                    contentPadding = contentPadding,
                    eventId = it,
                    onClickMorePeople = { eventId ->
                        navController.navigate(
                            route = "${Main.People.route}/${eventId}"
                        )
                    },
                    onClickOrganizer = { communityId ->
                        navController.navigate(
                            route = "${Main.CommunityDetails.route}/${communityId}"
                        )
                    }
                )
            }
        }
        composable(
            route = "${Main.CommunityDetails.route}/{$COMMUNITY_ID}",
            arguments = listOf(
                navArgument(COMMUNITY_ID) { type = NavType.IntType },
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(COMMUNITY_ID)?.let {
                CommunityDetailsScreen(
                    communityId = it,
                    contentPadding = contentPadding,
                    onClickEvent = { event ->
                        navController.navigate(
                            route = "${Main.EventDetails.route}/${event.id}"
                        )
                    },
                    onClickMorePeople = { communityId ->
                        navController.navigate(
                            route = "${Main.People.route}/${communityId}"
                        )
                    }
                )
            }
        }
        composable(
            route = "${Main.People.route}/{${EVENT_ID}}",
            arguments = listOf(
                navArgument(EVENT_ID) { type = NavType.IntType },
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(EVENT_ID)?.let {
                PeopleScreen(
                    contentPadding = contentPadding,
                    eventId = it
                )
            }
        }
        composable(
            route = "${Main.People.route}/{${COMMUNITY_ID}}",
            arguments = listOf(
                navArgument(COMMUNITY_ID) { type = NavType.IntType },
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(COMMUNITY_ID)?.let {
                PeopleScreen(
                    contentPadding = contentPadding,
                    eventId = it
                )
            }
        }
    }
}

sealed class Main(val route: String) {
    object Home : Main(route = "main_screen")
    object EventDetails : Main(route = "event_details")
    object People : Main(route = "people")
    object CommunityDetails : Main(route = "community_details")
}

private const val EVENT_ID = "event_id"
private const val COMMUNITY_ID = "community_id"