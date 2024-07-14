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
import com.example.composeprotject.screen.splashScreen.SplashScreen
import com.example.composeprotject.screen.verification.CreateProfileScreen
import com.example.composeprotject.screen.verification.VerifInputPhoneNumberScreen
import com.example.composeprotject.screen.verification.VerificationCodeScreen
import com.example.composeprotject.viewModel.AuthViewModel
import com.example.composeprotject.viewModel.EventDetailsViewModel
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.SplashScreenViewModel

@ExperimentalFoundationApi
@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    contentPadding: PaddingValues,
    mainViewModel: MainViewModel,
    splashScreenViewModel: SplashScreenViewModel,
    eventDetailsViewModel: EventDetailsViewModel,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.SplashScreenItem.route
    ) {
        composable(route = NavItem.EventItem.route) {
            EventScreen(
                viewModel = mainViewModel,
                contentPadding = contentPadding,
                navController = navController
            )
        }

        composable(route = NavItem.CommunityItem.route) {
            CommunityScreen(
                viewModel = mainViewModel,
                contentPadding = contentPadding,
                navController = navController,
                onCommunityClick = { community ->
                    navController.navigate(
                        route = "${NavItem.CommunityDetailsItem.route}/" +
                                "${community.id}/${community.nameGroup}"
                    )
                })
        }

        composable(route = NavItem.StillItem.route) {
            StillScreen(
                viewModel = mainViewModel,
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

        composable(route = NavItem.MyMeetingsScreen.route){
            MyMeetingsScreen(
                viewModel = mainViewModel,
                contentPadding = contentPadding,
                navController = navController
            )
        }

        composable(route = NavItem.ProfileItem.route){
            ProfileScreen(viewModel = mainViewModel, contentPadding = contentPadding)
        }

        composable(
            route = "${NavItem.CommunityDetailsItem.route}/{$COMMUNITY_ID}/{$COMMUNITY_NAME}",
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
                viewModel = mainViewModel
            )
        }

        composable(
            route = "${NavItem.EventDetailsItem.route}/{$EVENT_ID}/{$EVENT_NAME}",
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
                mainViewModel = mainViewModel,
                eventDetailsViewModel = eventDetailsViewModel
            )
        }

        composable(route = NavItem.SplashScreenItem.route){
            SplashScreen(
                splashScreenViewModel = splashScreenViewModel,
                navController = navController,
                contentPadding = contentPadding,
                mainViewModel = mainViewModel)
        }
        
        composable(route = NavItem.VerificationCodeScreenItem.route){
            VerificationCodeScreen(
                phoneNumber = "+7 999 999-99-99",
                contentPadding = contentPadding,
                authViewModel = authViewModel,
                navController = navController,
                mainViewModel = mainViewModel
            )
        }

        composable(route = NavItem.CreateProfileScreenItem.route){
            CreateProfileScreen(
                contentPadding = contentPadding,
                navController = navController,
                mainViewModel = mainViewModel
            )
        }

        composable(route = NavItem.VerifInputPhoneNumberScreenItem.route){
            VerifInputPhoneNumberScreen(
                contentPadding = contentPadding,
                navController = navController,
                authViewModel = authViewModel,
                mainViewModel = mainViewModel)
        }
    }
}

private const val EVENT_ID = "eventId"
private const val EVENT_NAME = "eventName"
private const val COMMUNITY_ID = "communityId"
private const val COMMUNITY_NAME = "communityName"