package com.example.composeprotject.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.common.utils_ui.EventScreenVariant
import com.example.composeprotject.R
import com.example.composeprotject.ui.component_old.tab.EventTab
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun MyEventScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    navController: NavHostController
) {
    val tabs = listOf(R.string.text_tab_my_event_plan, R.string.text_tab_my_event_passed)

    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24),
    ) {
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        EventTab(
            tabs = tabs,
            eventScreenVariant = EventScreenVariant.MY_EVENT_SCREEN,
            navController = navController
        )
    }
}