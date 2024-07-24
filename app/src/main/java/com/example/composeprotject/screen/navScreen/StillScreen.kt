package com.example.composeprotject.screen.navScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.divider.StandardDivider
import com.example.composeprotject.ui.component.menuItem.MyEventMenuItem
import com.example.composeprotject.ui.component.menuItem.ProfileMenuItem
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.utils.getUserFullName
import com.example.composeprotject.viewModel.nav.StillViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StillScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    stillViewModel: StillViewModel = koinViewModel(),
    onStillClickToProfileScreen: (/*TODO*/) -> Unit,
    onStillClickToMyMeetingsScreen: (/*TODO*/) -> Unit
) {
    val userInfo by stillViewModel.shortInfoUserFlow().collectAsState()

    Column(
        modifier = modifier
            .padding(contentPadding)
    ) {
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        userInfo?.let { user ->
            ProfileMenuItem(
                name = getUserFullName(user.userName, user.userSurname),
                numberPhone = user.phoneNumber,
                avatarUrl = user.avatarUrl,
                onClick = { onStillClickToProfileScreen(/*TODO*/) }
            )
        }
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        MyEventMenuItem(
            onClick = { onStillClickToMyMeetingsScreen(/*TODO*/) },
            menuIcon = R.drawable.ic_still_nav_menu_my_event,
            menuName = R.string.text_my_event
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        MyEventMenuItem(
            onClick = {  /*TODO*/ },
            menuIcon = R.drawable.ic_still_nav_menu_theme,
            menuName = R.string.text_theme
        )
        MyEventMenuItem(
            onClick = {  /*TODO*/ },
            menuIcon = R.drawable.ic_still_nav_menu_theme,
            menuName = R.string.text_notifications
        )
        MyEventMenuItem(
            onClick = {  /*TODO*/ },
            menuIcon = R.drawable.ic_stiil_nav_menu_safety,
            menuName = R.string.text_safety
        )
        MyEventMenuItem(
            onClick = {  /*TODO*/ },
            menuIcon = R.drawable.ic_stiil_nav_menu_memory_and_resources,
            menuName = R.string.text_memory_and_resources
        )
        StandardDivider()
        MyEventMenuItem(
            onClick = {  /*TODO*/ },
            menuIcon = R.drawable.ic_still_nav_menu_help,
            menuName = R.string.text_help
        )
        MyEventMenuItem(
            onClick = { /*TODO*/ },
            menuIcon = R.drawable.ic_still_nav_menu_invite_friend,
            menuName = R.string.text_invite_friend
        )
    }
}
