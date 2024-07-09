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
import com.example.composeprotject.screen.navScreen.EventScreen
import com.example.composeprotject.screen.splashScreen.SplashScreen
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.SplashScreenViewModel

@ExperimentalFoundationApi
@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    contentPadding : PaddingValues,
    mainViewModel: MainViewModel,
    splashScreenViewModel: SplashScreenViewModel
    ) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.SplashScreenItem.route
    ) {
        composable(BottomNavItem.Event.route) {
            EventScreen(viewModel = mainViewModel, contentPadding = contentPadding)
        }

        composable(BottomNavItem.Community.route) {
            MyMeetingsScreen(viewModel = mainViewModel, contentPadding = contentPadding) //TODO: поменять на CommunityScreen
        }

        composable(BottomNavItem.Still.route) {
            ProfileScreen(viewModel = mainViewModel, contentPadding = contentPadding) //TODO: поменять на StillScreen
        }

        composable(route = BottomNavItem.SplashScreenItem.route){
            SplashScreen(
                viewModel = splashScreenViewModel,
                navController = navController,
                contentPadding = contentPadding,
                mainViewModel = mainViewModel)
        }
    }
}