package com.example.composeprotject.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.screen.MyMeetingsScreen
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
        startDestination = BottomNavItem.Event.route
    ) {
        composable(BottomNavItem.Event.route) {
            EventScreen(viewModel = viewModel, contentPadding = contentPadding)
        }

        composable(BottomNavItem.Community.route) {
            CommunityScreen(viewModel = viewModel, contentPadding = contentPadding)
        }

        composable(BottomNavItem.Still.route) {
            ProfileScreen(viewModel = viewModel, contentPadding = contentPadding) //TODO: поменять на StillScreen
        }
    }
}