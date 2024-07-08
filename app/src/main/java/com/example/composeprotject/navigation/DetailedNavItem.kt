package com.example.composeprotject.navigation

import com.example.composeprotject.R

sealed class DetailedNavItem(
    val route : String,
    val name : Int
) {
    data object CommunityDetailsItem : DetailedNavItem(
        route = "COMMUNITY_DETAILS",
        name = R.string.text_community_details
    )
}