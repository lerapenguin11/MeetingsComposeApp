package com.example.composeprotject.screen.tabScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.common.ActiveEventVariant
import com.example.composeprotject.model.Event
import com.example.composeprotject.ui.component.card.EventCard

@Composable
fun MeetingsScreen(activeEvent: ActiveEventVariant, navController: NavHostController) {
    LazyColumn {
        items(
            when(activeEvent){
                ActiveEventVariant.ALL_EVENT -> eventList()
                ActiveEventVariant.ACTIVE_EVENT -> eventList().filter { it.active }
                ActiveEventVariant.INACTIVE_EVENT -> eventList().filter { !it.active }
            }
        ){ item ->
            EventCard(
                meetingName = item.meetingName,
                dateLocation = item.dateLocation,
                tags = item.tags,
                avatarUrl = item.avatarUrl,
                placeholderImage = R.drawable.ic_avatar_meetings,
                isActiveMeet = item.active,
                onClick = {navController.navigate("detailed")}
            )
        }
    }
}

fun eventList() = arrayListOf<Event>(
    Event(
        eventId = 0,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    ),
    Event(
        eventId = 1,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = false
    ),
    Event(
        eventId = 2,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = false
    ),
    Event(
        eventId = 3,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    ),
    Event(
        eventId = 4,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    ),
    Event(
        eventId = 5,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    ),
    Event(
        eventId = 6,
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    )
)