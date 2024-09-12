package com.example.composeprotject.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composeprotject.R
import com.example.composeprotject.nav.route.Graph
import com.example.composeprotject.screen.details.CommunityDetailsScreen
import com.example.composeprotject.screen.details.EventDetailsScreen
import com.example.composeprotject.screen.main.MainScreen
import com.example.composeprotject.screen.people.PeopleScreen
import com.example.composeprotject.screen.state.PeopleState
import com.example.composeprotject.ui.component.button.TopAppBarImageButton
import com.example.composeprotject.ui.component.text.TopAppBarTextWithBackArrow
import com.example.composeprotject.ui.component.topBar.ProvideAppBarAction
import com.example.composeprotject.ui.component.topBar.ProvideAppBarTitle
import com.example.composeprotject.ui.component.utils.CommonString

@Composable
fun MainGraph(navController: NavHostController, contentPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Main.Home.route,
        route = Graph.MAIN
    ) {
        composable(route = Main.Home.route) {
            MainScreen(
                contentPadding = contentPadding,
                onClickEvent = { meeting ->
                    navController.navigate(
                        route = "${Main.EventDetails.route}/${meeting.id}/${meeting.title}"
                    )
                },
                onClickCommunity = { community ->
                    navController.navigate(
                        route = "${Main.CommunityDetails.route}/${community.id}/${community.title}"
                    )
                }
            )
        }
        composable(
            route = "${Main.EventDetails.route}/{$EVENT_ID}/{$EVENT_TITLE}",
            arguments = listOf(
                navArgument(EVENT_ID) { type = NavType.IntType },
                navArgument(EVENT_TITLE) { type = NavType.StringType }
            )
        ) { backStackEntry ->

            backStackEntry.arguments?.getInt(EVENT_ID)?.let { eventId ->
                EventDetailsScreen(
                    contentPadding = contentPadding,
                    eventId = eventId,
                    onClickMorePeople = {
                        navController.navigate(
                            route = "${Main.PeopleEvent.route}/${it}"
                        )
                    },
                    onClickOrganizer = { community ->
                        navController.navigate(
                            route = "${Main.CommunityDetails.route}/${community.id}/${community.name}"
                        )
                    },
                    onClickEvent = { event ->
                        navController.navigate(
                            route = "${Main.EventDetails.route}/${event.id}/${event.title}"
                        )
                    }
                )
            }
            backStackEntry.arguments?.getString(EVENT_TITLE)?.let { title ->
                ProvideAppBarTitle(title = {
                    TopAppBarTextWithBackArrow(text = title)
                })
            }
            ProvideAppBarAction(actions = {
                TopAppBarImageButton(
                    onClick = {
                        //TODO
                    },
                    content = R.drawable.ic_share
                )
            })
        }
        composable(
            route = "${Main.CommunityDetails.route}/{$COMMUNITY_ID}/{$COMMUNITY_TITLE}",
            arguments = listOf(
                navArgument(COMMUNITY_ID) { type = NavType.IntType },
                navArgument(COMMUNITY_TITLE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(COMMUNITY_ID)?.let {
                CommunityDetailsScreen(
                    communityId = it,
                    contentPadding = contentPadding,
                    onClickEvent = { event ->
                        navController.navigate(
                            route = "${Main.EventDetails.route}/${event.id}"
                        )
                    },
                    onClickMorePeople = { communityId ->
                        navController.navigate(
                            route = "${Main.PeopleCommunity.route}/${communityId}"
                        )
                    }
                )
            }
            backStackEntry.arguments?.getString(COMMUNITY_TITLE)?.let { title ->
                ProvideAppBarTitle(title = {
                    TopAppBarTextWithBackArrow(
                        text = title
                    )
                })
            }
            ProvideAppBarAction(actions = {
                TopAppBarImageButton(
                    onClick = {
                        //TODO
                    },
                    content = R.drawable.ic_share
                )
            })
        }
        composable(
            route = "${Main.PeopleEvent.route}/{${EVENT_ID}}",
            arguments = listOf(
                navArgument(EVENT_ID) { type = NavType.IntType },
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(EVENT_ID)?.let {
                PeopleScreen(
                    contentPadding = contentPadding,
                    id = it,
                    screenState = PeopleState.EVENT_PEOPLE
                )
                ProvideAppBarTitle(title = {
                    TopAppBarTextWithBackArrow(
                        text = stringResource(id = CommonString.text_will_meet_halfway)
                    )
                })
            }
        }
        composable(
            route = "${Main.PeopleCommunity.route}/{${COMMUNITY_ID}}",
            arguments = listOf(
                navArgument(COMMUNITY_ID) { type = NavType.IntType },
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(COMMUNITY_ID)?.let {
                PeopleScreen(
                    id = it,
                    contentPadding = contentPadding,
                    screenState = PeopleState.SUBSCRIBERS
                )
                ProvideAppBarTitle(title = {
                    TopAppBarTextWithBackArrow(
                        text = stringResource(CommonString.text_subscribers)
                    )
                })
            }
        }
    }
}

sealed class Main(val route: String) {
    object Home : Main(route = "main_screen")
    object EventDetails : Main(route = "event_details")
    object PeopleEvent : Main(route = "people_event")
    object PeopleCommunity : Main(route = "people_community")
    object CommunityDetails : Main(route = "community_details")
}

private const val EVENT_ID = "event_id"
private const val COMMUNITY_ID = "community_id"
private const val COMMUNITY_TITLE = "community_title"
private const val EVENT_TITLE = "event_title"