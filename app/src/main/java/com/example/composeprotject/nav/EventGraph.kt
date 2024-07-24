package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.screen.MyEventScreen
import com.example.composeprotject.screen.ProfileScreen
import com.example.composeprotject.screen.detailsScreen.CommunityDetailsScreen
import com.example.composeprotject.screen.detailsScreen.EventDetailsScreen
import com.example.composeprotject.screen.navScreen.CommunityScreen
import com.example.composeprotject.screen.navScreen.EventScreen
import com.example.composeprotject.screen.navScreen.StillScreen

@Composable
fun Event(navController: NavHostController, contentPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavItem.EventItem.route,
        route = Graph.EVENT
    ) {
        composable(route = NavItem.EventItem.route) {
            EventScreen(
                contentPadding = contentPadding,
                navController = navController
            )
        }

        composable(route = NavItem.StillItem.route) {
            StillScreen(
                contentPadding = contentPadding,
                onStillClickToProfileScreen = {
                    navController.navigate(
                        route = NavItem.ProfileItem.route
                    )
                },
                onStillClickToMyMeetingsScreen = {
                    navController.navigate(
                        route = NavItem.MyMeetingsScreen.route
                    )
                })
        }

        composable(route = NavItem.CommunityItem.route) {
            CommunityScreen(
                contentPadding = contentPadding,
                onCommunityClick = { community ->
                    navController.navigate(
                        route = "${NavItem.CommunityDetailsItem.route}/" +
                                "${community.id}/${community.nameGroup}"
                    )
                })
        }

        composable(
            route = "${NavItem.EventDetailsItem.route}/{${Params.EVENT_ID}}/{${Params.EVENT_NAME}}",
            arguments = listOf(
                navArgument(Params.EVENT_ID) { type = NavType.IntType },
                navArgument(Params.EVENT_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt(Params.EVENT_ID)
            val eventName = backStackEntry.arguments?.getString(Params.EVENT_NAME)
            EventDetailsScreen(
                contentPadding = contentPadding,
                eventId = eventId,
                eventName = eventName
            )
        }

        composable(
            route = "${NavItem.CommunityDetailsItem.route}/{${Params.COMMUNITY_ID}}/{${Params.COMMUNITY_NAME}}",
            arguments = listOf(
                navArgument(Params.COMMUNITY_ID) { type = NavType.IntType },
                navArgument(Params.COMMUNITY_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val communityId = backStackEntry.arguments?.getInt(Params.COMMUNITY_ID)
            val communityName = backStackEntry.arguments?.getString(Params.COMMUNITY_NAME)
            CommunityDetailsScreen(
                navController = navController,
                communityId = communityId,
                contentPadding = contentPadding,
                communityName = communityName
            )
        }

        composable(route = NavItem.MyMeetingsScreen.route) {
            MyEventScreen(
                contentPadding = contentPadding,
                navController = navController
            )
        }

        composable(route = NavItem.ProfileItem.route) {
            ProfileScreen(contentPadding = contentPadding)
        }
    }
}