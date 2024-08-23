package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.screen.MyEventScreen
import com.example.composeprotject.screen.ProfileScreen
import com.example.composeprotject.screen.detailsScreen.CommunityDetailsScreen
import com.example.composeprotject.screen.detailsScreen.EventDetailsScreen
import com.example.composeprotject.screen.navScreen.CommunityScreen
import com.example.composeprotject.screen.navScreen.EventScreen
import com.example.composeprotject.screen.navScreen.StillScreen
import com.example.composeprotject.ui.component.text.TopAppBarText
import com.example.composeprotject.ui.component.text.TopAppBarTextWithBackArrow
import com.example.composeprotject.ui.component_old.toolbar.newTopBar.ProvideAppBarAction
import com.example.composeprotject.ui.component_old.toolbar.newTopBar.ProvideAppBarTitle

@Composable
fun EventGraph(navController: NavHostController, contentPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavItem.EventItem.route,
        route = Graph.EVENT
    ) {
        composable(route = NavItem.EventItem.route) {
            ProvideAppBarTitle(title = {
                TopAppBarText(text = stringResource(id = NavItem.EventItem.name))
            })
            ProvideAppBarAction(actions = {
                IconButton(
                    onClick = {/*TODO*/ },
                    content = {
                        Icon(painterResource(id = R.drawable.ic_add_event), null)
                    }
                )
            })
            EventScreen(
                contentPadding = contentPadding,
                navController = navController
            )
        }

        composable(route = NavItem.StillItem.route) {
            ProvideAppBarTitle(title = {
                TopAppBarText(text = stringResource(id = NavItem.StillItem.name))
            })
            StillScreen(
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

        composable(route = NavItem.CommunityItem.route) {
            ProvideAppBarTitle(title = {
                TopAppBarText(text = stringResource(id = NavItem.CommunityItem.name))
            })
            CommunityScreen(
                contentPadding = contentPadding,
                onCommunityClick = { community ->

                    navController.popBackStack(
                        route = NavItem.CommunityItem.route,
                        inclusive = false
                    )

                    navController.navigate(
                        route = "${NavItem.CommunityDetailsItem.route}/" +
                                "${community.id}/${community.nameGroup}"
                    )
                })
        }

        composable(
            route = "${NavItem.EventDetailsItem.route}/{${Params.EVENT_ID}}/{${Params.EVENT_NAME}}",
            arguments = listOf(
                navArgument(Params.EVENT_ID) { type = NavType.IntType },
                navArgument(Params.EVENT_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Params.EVENT_NAME)?.let {
                ProvideAppBarTitle(title = {
                    TopAppBarTextWithBackArrow(text = it)
                })
            }
            backStackEntry.arguments?.getInt(Params.EVENT_ID)?.let {
                EventDetailsScreen(
                    contentPadding = contentPadding,
                    eventId = it
                )
            }
        }

        composable(
            route = "${NavItem.CommunityDetailsItem.route}/{${Params.COMMUNITY_ID}}/{${Params.COMMUNITY_NAME}}",
            arguments = listOf(
                navArgument(Params.COMMUNITY_ID) { type = NavType.IntType },
                navArgument(Params.COMMUNITY_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Params.COMMUNITY_NAME)?.let {
                ProvideAppBarTitle(title = {
                    TopAppBarTextWithBackArrow(text = it)
                })
            }
            backStackEntry.arguments?.getInt(Params.COMMUNITY_ID)?.let {
                CommunityDetailsScreen(
                    communityId = it,
                    contentPadding = contentPadding
                )
            }
        }

        composable(route = NavItem.MyMeetingsScreen.route) {
            ProvideAppBarTitle(title = {
                TopAppBarTextWithBackArrow(text = stringResource(id = NavItem.MyMeetingsScreen.name))
            })
            MyEventScreen(
                contentPadding = contentPadding,
                navController = navController
            )
        }

        composable(route = NavItem.ProfileItem.route) {
            ProvideAppBarTitle(title = {
                TopAppBarTextWithBackArrow(text = stringResource(id = NavItem.ProfileItem.name))
            })
            ProvideAppBarAction(actions = {
                IconButton(
                    onClick = {/*TODO*/ },
                    content = {
                        Icon(painterResource(id = R.drawable.ic_edit_profile), null)
                    }
                )
            })
            ProfileScreen(contentPadding = contentPadding)
        }
    }
}