package com.example.composeprotject.screen.navScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.composeprotject.R
import com.example.composeprotject.navigation.BottomNavItem
import com.example.composeprotject.common.EventScreenVariant
import com.example.composeprotject.ui.component.input.CustomSearchOutlinedTextField
import com.example.composeprotject.ui.component.tab.EventTab
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    contentPadding : PaddingValues
){
    viewModel.setCurrentScreen(BottomNavItem.Event)
    val tabs = listOf(R.string.text_tab_all_events, R.string.text_tab_active_events)

    Column(
        modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24),
    ) {
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX16))
        CustomSearchOutlinedTextField(
            textPlaceholder = stringResource(id = R.string.text_placeholder_input_search),
            isEnabled = true)
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX16))
        EventTab(tabs = tabs, eventScreenVariant = EventScreenVariant.EVENT_SCREEN)
    }
}