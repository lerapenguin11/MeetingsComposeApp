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
import com.example.composeprotject.screen.detailsScreen.CommunityDetailsScreen
import com.example.composeprotject.screen.ProfileScreen
import com.example.composeprotject.screen.detailsScreen.EventDetailsScreen
import com.example.composeprotject.screen.navScreen.CommunityScreen
import com.example.composeprotject.screen.navScreen.EventScreen
import com.example.composeprotject.viewModel.MainViewModel

@ExperimentalFoundationApi
@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    contentPadding : PaddingValues,
    viewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.EventItem.route
    ) {
        composable(BottomNavItem.EventItem.route) {
            EventScreen(viewModel = viewModel, contentPadding = contentPadding, navController = navController)
        }

        composable(BottomNavItem.CommunityItem.route) {
            CommunityScreen(
                viewModel = viewModel,
                contentPadding = contentPadding,
                navController = navController,
                onCommunityClick = { community ->
                    navController.navigate(
                        route = "${BottomNavItem.CommunityDetailsItem.route}/${community.id}/${community.nameGroup}")
                })
        }

        composable(BottomNavItem.StillItem.route) {
            ProfileScreen(viewModel = viewModel, contentPadding = contentPadding) //TODO: поменять на StillScreen
        }
        composable(
            route = "${BottomNavItem.CommunityDetailsItem.route}/{communityId}/{communityName}",
            arguments = listOf(
                navArgument("communityId") { type = NavType.IntType },
                navArgument("communityName"){type = NavType.StringType}
            )
        ) { backStackEntry ->
            val communityId = backStackEntry.arguments?.getInt("communityId")
            val communityName = backStackEntry.arguments?.getString("communityName")
            CommunityDetailsScreen(
                navController = navController,
                communityId = communityId,
                contentPadding = contentPadding,
                communityName = communityName,
                viewModel = viewModel)
        }
        composable("detailed") { backStackEntry ->
            EventDetailsScreen(
                contentPadding = contentPadding/*backStackEntry.arguments?.getInt("id")*/)
        }
    }
}