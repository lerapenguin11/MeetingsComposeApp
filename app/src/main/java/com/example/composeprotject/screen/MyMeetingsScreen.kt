package com.example.composeprotject.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.common.EventScreenVariant
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.tab.EventTab
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun MyMeetingsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    contentPadding: PaddingValues,
    navController: NavHostController
) {
    viewModel.setCurrentScreen(screen = NavItem.MyMeetingsScreen, show = true)
    val tabs = listOf(R.string.text_tab_my_event_plan, R.string.text_tab_my_event_passed)

    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24),
    ) {
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX16))
        EventTab(
            tabs = tabs,
            eventScreenVariant = EventScreenVariant.MY_EVENT_SCREEN,
            navController = navController
        )
    }
}