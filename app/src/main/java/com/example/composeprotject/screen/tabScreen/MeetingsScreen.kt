package com.example.composeprotject.screen.tabScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.common.EventVariant
import com.example.composeprotject.domain.model.nav.Event
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.viewModel.nav.EventViewModel
import org.koin.androidx.compose.koinViewModel

//TODO: расшарить eventViewModel на MeetingsScreen и EventScreen

@Composable
fun MeetingsScreen(
    eventVariant: EventVariant,
    navController: NavHostController,
    eventViewModel: EventViewModel = koinViewModel() ) {

    eventViewModel.getEvents(variantEvent = eventVariant.name)
    val eventList by eventViewModel.getEventsFlow().collectAsState()

    LazyColumn {
        items(eventList) { event ->
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