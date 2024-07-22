package com.example.composeprotject.screen.navScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.input.CustomSearchOutlinedTextFieldIcon
import com.example.composeprotject.ui.component.tab.EventTab
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    contentPadding: PaddingValues,
    navController: NavHostController
) {
    viewModel.setCurrentScreen(screen = NavItem.EventItem, showTopBar = true, showBottomBar = true)
    val tabs = listOf(R.string.text_tab_all_events, R.string.text_tab_active_events)

    Column(
        modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
    ) {
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        CustomSearchOutlinedTextFieldIcon(
            textPlaceholder = stringResource(id = R.string.text_placeholder_input_search),
            isEnabled = true
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        EventTab(
            tabs = tabs,
            eventScreenVariant = com.example.common.utils_ui.EventScreenVariant.EVENT_SCREEN,
            navController = navController
        )
    }
}