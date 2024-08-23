package com.example.composeprotject.ui.component_old.toolbar.newTopBar

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.res.painterResource
import androidx.navigation.FloatingWindow
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.theme.MeetTheme
import kotlinx.coroutines.flow.filterNot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    navController: NavController
) {
    val currentContentBackStackEntry by produceState(
        initialValue = null as NavBackStackEntry?,
        producer = {
            navController.currentBackStackEntryFlow
                .filterNot { it.destination is FloatingWindow }
                .collect { value = it }
        }
    )
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MeetTheme.colors.neutralWhite),
        navigationIcon = {
            val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current
            val excludedRoutes = listOf(
                NavItem.StillItem.route,
                NavItem.EventItem.route,
                NavItem.CommunityItem.route
            )
            val isBackPress =
                navController.previousBackStackEntry != null && !excludedRoutes.contains(
                    navController.currentDestination?.route
                )

            if (isBackPress) {
                IconButton(
                    onClick = { backPressDispatcher?.onBackPressedDispatcher?.onBackPressed() },
                    content = {
                        Icon(painterResource(id = R.drawable.ic_arrow_back), null)
                    }
                )
            }
        },
        title = {
            AppBarTitle(currentContentBackStackEntry)
        },
        actions = {
            AppBarAction(currentContentBackStackEntry)
        }
    )
}