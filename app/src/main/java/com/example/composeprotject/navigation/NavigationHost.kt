package com.example.composeprotject.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeprotject.screen.MyMeetingsScreen
import com.example.composeprotject.screen.ProfileScreen
import com.example.composeprotject.screen.detailsScreen.CommunityDetailsScreen
import com.example.composeprotject.screen.detailsScreen.EventDetailsScreen
import com.example.composeprotject.screen.navScreen.CommunityScreen
import com.example.composeprotject.screen.navScreen.EventScreen
import com.example.composeprotject.screen.navScreen.StillScreen
import com.example.composeprotject.viewModel.MainViewModel

@ExperimentalFoundationApi
@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    contentPadding: PaddingValues,
    viewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.EventItem.route
    ) {
        composable(route = BottomNavItem.EventItem.route) {
            EventScreen(
                viewModel = viewModel,
                contentPadding = contentPadding,
                navController = navController
            )
        }

        composable(route = BottomNavItem.CommunityItem.route) {
            CommunityScreen(
                viewModel = viewModel,
                contentPadding = contentPadding,
                navController = navController,
                onCommunityClick = { community ->
                    navController.navigate(
                        route = "${BottomNavItem.CommunityDetailsItem.route}/" +
                                "${community.id}/${community.nameGroup}"
                    )
                })
        }

        composable(route = BottomNavItem.StillItem.route) {
            StillScreen(
                viewModel = viewModel,
                contentPadding = contentPadding,
                onStillClickToProfileScreen = {
                    navController.navigate(
                        route = BottomNavItem.ProfileItem.route
                    )
                },
                onStillClickToMyMeetingsScreen = {
                    navController.navigate(
                        route = BottomNavItem.MyMeetingsScreen.route
                    )
                })
        }

        composable(route = BottomNavItem.MyMeetingsScreen.route){
            MyMeetingsScreen(
                viewModel = viewModel,
                contentPadding = contentPadding,
                navController = navController
            )
        }

        composable(route = BottomNavItem.ProfileItem.route){
            ProfileScreen(viewModel = viewModel, contentPadding = contentPadding)
        }

        composable(
            route = "${BottomNavItem.CommunityDetailsItem.route}/{$COMMUNITY_ID}/{$COMMUNITY_NAME}",
            arguments = listOf(
                navArgument(COMMUNITY_ID) { type = NavType.IntType },
                navArgument(COMMUNITY_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val communityId = backStackEntry.arguments?.getInt(COMMUNITY_ID)
            val communityName = backStackEntry.arguments?.getString(COMMUNITY_NAME)
            CommunityDetailsScreen(
                navController = navController,
                communityId = communityId,
                contentPadding = contentPadding,
                communityName = communityName,
                viewModel = viewModel
            )
        }

        composable(
            route = "${BottomNavItem.EventDetailsItem.route}/{$EVENT_ID}/{$EVENT_NAME}",
            arguments = listOf(
                navArgument(EVENT_ID) { type = NavType.IntType },
                navArgument(EVENT_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt(EVENT_ID)
            val eventName = backStackEntry.arguments?.getString(EVENT_NAME)
            EventDetailsScreen(
                contentPadding = contentPadding,
                eventId = eventId,
                eventName = eventName,
                viewModel = viewModel
            )
        }
    }
}

private const val EVENT_ID = "eventId"
private const val EVENT_NAME = "eventName"
private const val COMMUNITY_ID = "communityId"
private const val COMMUNITY_NAME = "communityName"