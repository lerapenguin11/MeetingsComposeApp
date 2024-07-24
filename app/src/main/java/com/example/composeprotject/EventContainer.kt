package com.example.composeprotject

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeprotject.nav.Event
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.navigation.navComponent.BottomNavigationBar

@Composable
fun EventContainer(navController: NavHostController = rememberNavController()) {
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
        bottomBar = {
            when {
                bottomBarIsShow.value -> {
                    BottomNavigationBar(navController)
                }
            }
        }
    ) { contentPadding ->
        Event(navController = navController, contentPadding = contentPadding)
    }
}