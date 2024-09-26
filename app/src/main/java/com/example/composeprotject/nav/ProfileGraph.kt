package com.example.composeprotject.nav

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.composeprotject.R
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.screen.profile.EditProfileScreen
import com.example.composeprotject.screen.profile.ProfileScreen
import com.example.composeprotject.ui.component.button.TopAppBarImageButton
import com.example.composeprotject.ui.component.topBar.standard.ProvideAppBarAction
import com.example.composeprotject.ui.component.utils.CommonDrawables

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = Profile.UserInfo.route
    ) {
        composable(
            route = Profile.UserInfo.route
        ) {
            ProvideAppBarAction(actions = {
                TopAppBarImageButton(
                    onClick = {
                        navController.navigate(route = Profile.EditInfo.route)
                    },
                    content = R.drawable.ic_edit_profile
                )
            })
            ProfileScreen(navController = navController) {
                navController.navigate(route = Graph.MAIN) {
                    popUpTo(route = Graph.MAIN) {
                        saveState = true
                        inclusive = true
                    }
                }
            }
        }
        composable(
            route = Profile.EditInfo.route
        ) {
            var isSaveData by remember { mutableStateOf(false) }
            ProvideAppBarAction(actions = {
                TopAppBarImageButton(
                    onClick = {
                        isSaveData = true
                    },
                    isAnim = isSaveData,
                    content = CommonDrawables.ic_done_edit
                )
            })
            EditProfileScreen(
                navController = navController,
                isSaveData = isSaveData,
                onGoProfileAfterSaving = {
                    if (it) {
                        navController.navigate(route = Profile.UserInfo.route) {
                            popUpTo(route = Profile.UserInfo.route) {
                                saveState = true
                                inclusive = true
                            }
                        }
                    }
                })
        }
    }
}

sealed class Profile(val route: String) {
    object UserInfo : Profile(route = "profile")
    object EditInfo : Profile(route = "edit_profile")
}