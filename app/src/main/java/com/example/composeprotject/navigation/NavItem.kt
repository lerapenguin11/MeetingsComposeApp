package com.example.composeprotject.navigation

import com.example.composeprotject.R

sealed class NavItem(
    val route: String,
    val name: Int,
    val icon: Int?
) {
    data object EventItem : NavItem(
        route = EVENT_SCREEN,
        name = R.string.text_event,
        icon = R.drawable.ic_menu_event,
    )

    data object CommunityItem : NavItem(
        route = COMMUNITY_SCREEN,
        name = R.string.text_community,
        icon = R.drawable.ic_menu_community
    )

    data object ProfileItem : NavItem(
        route = PROFILE_SCREEN,
        name = R.string.text_profile,
        icon = null
    )

    data object StillItem : NavItem(
        route = STILL_SCREEN,
        name = R.string.text_still,
        icon = R.drawable.ic_menu_still
    )

    data object CommunityDetailsItem : NavItem(
        route = COMMUNITY_DETAILS,
        name = R.string.text_community_details,
        icon = null
    )

    data object EventDetailsItem : NavItem(
        route = EVENT_DETAILS,
        name = R.string.text_event_details,
        icon = null
    )

    data object MyMeetingsScreen : NavItem(
        route = MY_MEETINGS_SCREEN,
        name = R.string.text_my_event,
        icon = null
    )

    data object SplashScreenItem : NavItem(
        route = SPLASH_SCREEN,
        name = R.string.text_splash_screen,
        icon = null
    )

    data object VerificationCodeScreenItem : NavItem(
        route = VER_CODE_SCREEN,
        name = R.string.text_ver_code_screen,
        icon = null
    )

    data object CreateProfileScreenItem : NavItem(
        route = CREATE_PROFILE_SCREEN,
        name = R.string.text_profile,
        icon = null
    )
}

val navItems = listOf(
    NavItem.EventItem,
    NavItem.CommunityItem,
    NavItem.StillItem
)

private const val EVENT_SCREEN = "EVENT_SCREEN"
private const val COMMUNITY_SCREEN = "COMMUNITY_SCREEN"
private const val STILL_SCREEN = "STILL_SCREEN"
private const val COMMUNITY_DETAILS = "COMMUNITY_DETAILS"
private const val EVENT_DETAILS = "EVENT_DETAILS"
private const val PROFILE_SCREEN = "PROFILE_SCREEN"
private const val MY_MEETINGS_SCREEN = "MY_MEETINGS_SCREEN"
private const val SPLASH_SCREEN = "SPLASH_SCREEN"
private const val VER_CODE_SCREEN = "VER_CODE_SCREEN"
private const val CREATE_PROFILE_SCREEN = "CREATE_PROFILE_SCREEN"