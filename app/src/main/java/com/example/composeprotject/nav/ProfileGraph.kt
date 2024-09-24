package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.screen.profile.EditProfileScreen
import com.example.composeprotject.screen.profile.ProfileScreen

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = Profile.UserInfo.route
    ) {
        composable(
            route = Profile.UserInfo.route
        ) {
            ProfileScreen()
        }
        composable(
            route = Profile.EditInfo.route
        ) {
            EditProfileScreen(contentPadding = PaddingValues())
        }
    }
}

sealed class Profile(val route: String) {
    object UserInfo : Profile(route = "profile")
    object EditInfo : Profile(route = "edit_profile")
}