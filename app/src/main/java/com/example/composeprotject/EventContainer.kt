package com.example.composeprotject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.nav_old.EventGraph
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.navigation.navComponent.BottomNavigationBar
import com.example.composeprotject.ui.component_old.toolbar.newTopBar.MyTopAppBar
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun EventContainer() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val baseRoute = currentRoute?.substringBefore("/") ?: ""
    val bottomBarIsShow = rememberSaveable { (mutableStateOf(true)) }

    bottomBarIsShow.value = when (baseRoute) {
        NavItem.EventDetailsItem.route,
        NavItem.CommunityDetailsItem.route,
        NavItem.ProfileItem.route,
        NavItem.MyMeetingsScreen.route -> false

        else -> true
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MeetTheme.colors.neutralWhite,
        topBar = {
            MyTopAppBar(navController = navController)
        },
        bottomBar = {
            when {
                bottomBarIsShow.value -> {
                    BottomNavigationBar(
                        navController = navController,
                        defaultItem = getDefaultItem(navController)
                    )
                }
            }
        }
    ) { contentPadding ->
        EventGraph(navController = navController, contentPadding = contentPadding)
    }
}

private fun getDefaultItem(navController: NavHostController): NavItem {
    return when (navController.currentDestination?.route) {
        NavItem.EventItem.route -> NavItem.EventItem
        NavItem.CommunityItem.route -> NavItem.CommunityItem
        NavItem.StillItem.route -> NavItem.StillItem
        else -> {
            NavItem.EventItem
        }
    }
}