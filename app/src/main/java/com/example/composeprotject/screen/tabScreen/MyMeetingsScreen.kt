package com.example.composeprotject.screen.tabScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.common.utils_ui.MyEventVariant
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component_old.card.EventCard
import com.example.composeprotject.viewModel.MyEventViewModel
import org.koin.androidx.compose.koinViewModel

//TODO: расшарить eventViewModel на MyMeetingsScreen и MyEventScreen

@Composable
fun MyMeetingsScreen(
    eventVariant: MyEventVariant,
    myEventViewModel: MyEventViewModel = koinViewModel(),
    navController: NavHostController
) {
    myEventViewModel.getMyEvents(variant = eventVariant.name)
    val myEventList by myEventViewModel.getMyEventsFlow().collectAsState()

    LazyColumn {
        items(myEventList) { event ->
            EventCard(
                meetingName = event.meetingName,
                dateLocation = event.dateLocation,
                tags = event.tags,
                avatarUrl = event.avatarUrl,
                placeholderImage = R.drawable.ic_avatar_meetings,
                isActiveMeet = event.active,
                onClick = {
                    navController.navigate(
                        route = "${NavItem.EventDetailsItem.route}/${event.eventId}/${event.meetingName}"
                    )
                }
            )
        }
    }
}