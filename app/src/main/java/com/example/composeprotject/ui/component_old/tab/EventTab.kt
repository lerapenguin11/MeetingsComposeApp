package com.example.composeprotject.ui.component_old.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.common.utils_ui.EventScreenVariant
import com.example.common.utils_ui.EventVariant
import com.example.common.utils_ui.MyEventVariant
import com.example.composeprotject.screen_old.tabScreen.MeetingsScreen
import com.example.composeprotject.screen_old.tabScreen.MyMeetingsScreen
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun EventTab(
    tabs: List<Int>,
    eventScreenVariant: EventScreenVariant,
    navController: NavHostController
) {
    val tabList = remember { Tab.entries.toTypedArray() }
    var tabIndex by remember { mutableIntStateOf(tabList.indexOf(Tab.FIRST)) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MeetTheme.colors.neutralWhite,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    height = MeetTheme.sizes.sizeX2,
                    color = MeetTheme.colors.brandDefault
                )
            },
            divider = {
                Spacer(modifier = Modifier.height(0.dp))
            }) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    unselectedContentColor = MeetTheme.colors.tabUnselectedContentColor,
                    selectedContentColor = MeetTheme.colors.brandDefault,
                    text = {
                        Text(
                            text = stringResource(id = title).uppercase(),
                            style = MeetTheme.typography.robotoMedium
                        )
                    },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }

        when (tabList[tabIndex]) {
            Tab.FIRST -> {
                if (eventScreenVariant == EventScreenVariant.EVENT_SCREEN) {
                    MeetingsScreen(
                        eventVariant = EventVariant.ALL_EVENT,
                        navController = navController
                    )
                } else {
                    MyMeetingsScreen(MyEventVariant.ACTIVE_EVENT,
                        navController = navController)
                }
            }

            Tab.SECOND -> {
                if (eventScreenVariant == EventScreenVariant.EVENT_SCREEN) {
                    MeetingsScreen(
                        eventVariant = EventVariant.ACTIVE_EVENT,
                        navController = navController
                    )
                } else {
                    MyMeetingsScreen(MyEventVariant.INACTIVE_EVENT,
                        navController = navController)
                }
            }
        }
    }
}

private enum class Tab {
    FIRST,
    SECOND
}