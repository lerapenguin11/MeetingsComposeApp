package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.screen.registration.SignInScreen
import com.example.composeprotject.screen.registration.SignInSuccessScreen

fun NavGraphBuilder.authGraph(contentPadding: PaddingValues, navController: NavHostController) {
    navigation(
        route = "${Graph.AUTH}?${EVENT_TITLE}={$EVENT_TITLE}?${EVENT_ID}={$EVENT_ID}" +
                "?$SHORT_ADDRESS={$SHORT_ADDRESS}?$START_DATE={$START_DATE}",
        arguments = listOf(
            navArgument(EVENT_TITLE) { type = NavType.StringType },
            navArgument(EVENT_ID) { type = NavType.IntType },
            navArgument(SHORT_ADDRESS) { type = NavType.StringType },
            navArgument(START_DATE) { type = NavType.LongType }
        ),
        startDestination = Auth.CheckIn.route
    ) {
        composable(
            route = Auth.CheckIn.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(
                    "${Graph.AUTH}?${EVENT_TITLE}={$EVENT_TITLE}?${EVENT_ID}={$EVENT_ID}?" +
                            "$SHORT_ADDRESS={$SHORT_ADDRESS}?$START_DATE={$START_DATE}"
                )
            }
            parentEntry.arguments?.getString(EVENT_TITLE)?.let { title ->
                parentEntry.arguments?.getString(SHORT_ADDRESS)?.let { shortAddress ->
                    parentEntry.arguments?.getInt(EVENT_ID)?.let { id ->
                        parentEntry.arguments?.getLong(START_DATE)?.let { date ->
                            SignInScreen(
                                title = title,
                                startDate = date,
                                shortAddress = shortAddress,
                                eventId = id,
                                contentPadding = contentPadding,
                                onCancelScreen = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
        composable(route = Auth.CheckInSuccess.route) {
            SignInSuccessScreen(contentPadding = contentPadding)
        }
    }
}

sealed class Auth(val route: String) {
    object CheckIn : Auth(route = "meeting_registration_and_check_in")
    object CheckInSuccess : Auth(route = "successful_registration_appointment")
}

private const val EVENT_ID = "event_id"
private const val EVENT_TITLE = "event_title"
private const val START_DATE = "startDate"
private const val SHORT_ADDRESS = "shortAddress"