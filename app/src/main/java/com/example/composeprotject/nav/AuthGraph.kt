package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.screen.verification.CreateProfileScreen
import com.example.composeprotject.screen.verification.VerifInputPhoneNumberScreen
import com.example.composeprotject.screen.verification.VerificationCodeScreen

fun NavGraphBuilder.auth(navController: NavController, contentPadding: PaddingValues) {
    navigation(
        startDestination = NavItem.VerifInputPhoneNumberScreenItem.route,
        route = Graph.AUTHENTICATION
    ) {
        composable(NavItem.VerifInputPhoneNumberScreenItem.route) {
            VerifInputPhoneNumberScreen(
                contentPadding = contentPadding,
                onSendCodePhoneNumberClick = { phoneNumber ->
                    navController.navigate(
                        route = "${NavItem.VerificationCodeScreenItem.route}/${phoneNumber}"
                    )
                })
        }

        composable(
            route = "${NavItem.VerificationCodeScreenItem.route}/{${Params.VER_PHONE_NUMBER}}",
            arguments = listOf(
                navArgument(Params.VER_PHONE_NUMBER) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Params.VER_PHONE_NUMBER)?.let {
                VerificationCodeScreen(
                    phoneNumber = it,
                    contentPadding = contentPadding,
                    onCreateProfile = {
                        navController.navigate(
                            route = NavItem.CreateProfileScreenItem.route
                        )
                    }
                )
            }
        }

        composable(route = NavItem.CreateProfileScreenItem.route) {
            CreateProfileScreen(
                contentPadding = contentPadding,
                onGoToGraphEvent = {
                    navController.navigate(Graph.EVENT) {
                        popUpTo(Graph.AUTHENTICATION) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
