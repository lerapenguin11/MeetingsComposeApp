package com.example.composeprotject.navigation

import com.example.composeprotject.R

sealed class BottomNavItem(
    val route: String,
    val name: Int,
    val icon: Int
) {
    data object Event : BottomNavItem(
        route = "EVENT_SCREEN",
        name = R.string.text_event,
        icon = R.drawable.ic_menu_event,
    )
    data object Community : BottomNavItem(
        route = "COMMUNITY_SCREEN",
        name = R.string.text_community,
        icon = R.drawable.ic_menu_community)
    data object Still : BottomNavItem(
        route = "STILL_SCREEN",
        name = R.string.text_still,
        icon = R.drawable.ic_menu_still)
}

val navItems = listOf(
    BottomNavItem.Event,
    BottomNavItem.Community,
    BottomNavItem.Still
)