package com.example.composeprotject.navigation.navComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.navigation.navItems
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    items: List<NavItem> = navItems,
    defaultItem : NavItem
) {
    var selectedMenuItem by remember { mutableStateOf(defaultItem) }

    NavigationBar(
        containerColor = MeetTheme.colors.neutralWhite,
    ) {
        items.forEach { navItem ->
            val isSelected = selectedMenuItem == navItem

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(unselectedIconColor = MeetTheme.colors.neutralActive),
                alwaysShowLabel = false,
                selected = false,
                label = null,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isSelected) {
                            BaseText(
                                text = stringResource(id = navItem.name),
                                textStyle = MeetTheme.typography.bodyText1
                            )
                            Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX4))
                            Box(
                                modifier = modifier
                                    .size(4.dp)
                                    .clip(CircleShape)
                                    .background(color = MeetTheme.colors.neutralActive)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = navItem.icon!!),
                                contentDescription = stringResource(id = navItem.name),
                            )
                        }
                    }
                },
                onClick = {
                    navController.navigate(navItem.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive = true
                        }
                    }
                    selectedMenuItem = navItem
                }
            )
        }
    }
}
