package com.example.composeprotject.ui.component.topBar

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.FloatingWindow
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.theme.MeetTheme
import kotlinx.coroutines.flow.filterNot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
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
    CenterAlignedTopAppBar(
        modifier = Modifier
            .height(40.dp),
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
                Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    IconButton(
                        onClick = {
                            backPressDispatcher?.onBackPressedDispatcher?.onBackPressed()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MeetTheme.colors.primary
                        ),
                        content = {
                            Icon(painterResource(id = R.drawable.ic_arrow_back), null)
                        }
                    )
                }
            }
        },
        title = {
            Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                AppBarTitle(currentContentBackStackEntry)
            }
        },
        actions = {
            Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                AppBarAction(currentContentBackStackEntry)
            }
        }
    )
}