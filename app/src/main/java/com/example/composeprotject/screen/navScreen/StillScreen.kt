package com.example.composeprotject.screen.navScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composeprotject.model.Community
import com.example.composeprotject.navigation.BottomNavItem
import com.example.composeprotject.ui.component.menuItem.MyEventMenuItem
import com.example.composeprotject.ui.component.menuItem.ProfileMenuItem
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun StillScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    contentPadding: PaddingValues,
    onStillClickToProfileScreen: (/*TODO*/) -> Unit,
    onStillClickToMyMeetingsScreen: (/*TODO*/) -> Unit,
) {
    viewModel.setCurrentScreen(BottomNavItem.StillItem)

    Column(
        modifier = modifier
            .padding(contentPadding)
    ) {
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX8))
        ProfileMenuItem(
            name = "Иван Иванов", //TODO
            numberPhone = "+7 999 999-99-99", //TODO
            avatarUrl = null,
            onClick = { onStillClickToProfileScreen(/*TODO*/) }
        )
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX8))
        MyEventMenuItem(
            onClick = { onStillClickToMyMeetingsScreen(/*TODO*/) }
        )
    }
}