package com.example.composeprotject.navigation

import com.example.composeprotject.R

sealed class BottomNavItem(
    val route: String,
    val name: Int,
    val icon: Int?
) {
    data object EventItem : BottomNavItem(
        route = "EVENT_SCREEN",
        name = R.string.text_event,
        icon = R.drawable.ic_menu_event,
    )

    data object CommunityItem : BottomNavItem(
        route = "COMMUNITY_SCREEN",
        name = R.string.text_community,
        icon = R.drawable.ic_menu_community
    )

    data object ProfileItem : BottomNavItem(
        route = "PROFILE_SCREEN",
        name = R.string.text_profile,
        icon = null
    )

    data object StillItem : BottomNavItem(
        route = "STILL_SCREEN",
        name = R.string.text_still,
        icon = R.drawable.ic_menu_still
    )

    data object CommunityDetailsItem : BottomNavItem(
        route = "COMMUNITY_DETAILS",
        name = R.string.text_community_details,
        icon = null
    )

    data object EventDetailsItem : BottomNavItem(
        route = "EVENT_DETAILS",
        name = R.string.text_event_details,
        icon = null
    )
}

val navItems = listOf(
    BottomNavItem.EventItem,
    BottomNavItem.CommunityItem,
    BottomNavItem.StillItem
)