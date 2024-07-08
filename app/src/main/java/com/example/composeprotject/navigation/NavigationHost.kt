package com.example.composeprotject.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.screen.ProfileScreen
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
            EventScreen(viewModel = viewModel, contentPadding = contentPadding)
        }

        composable(BottomNavItem.CommunityItem.route) {
            CommunityScreen(viewModel = viewModel, contentPadding = contentPadding)
        }

        composable(BottomNavItem.StillItem.route) {
            ProfileScreen(viewModel = viewModel, contentPadding = contentPadding) //TODO: поменять на StillScreen
        }
    }
}