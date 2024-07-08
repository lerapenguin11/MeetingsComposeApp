package com.example.composeprotject.screen.tabScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composeprotject.R
import com.example.composeprotject.common.ActiveEventVariant
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.divider.StandardDivider
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun MeetingsScreen(activeEvent: ActiveEventVariant) {
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
                onClick = {/*TODO*/}
            )
        }
    }
}

data class Event(
    val meetingName : String,
    val dateLocation : String,
    val tags : List<String>,
    val avatarUrl : String,
    val active : Boolean
)

fun eventList() = arrayListOf<Event>(
    Event(
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    ),
    Event(
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = false
    ),
    Event(
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = false
    ),
    Event(
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    ),
    Event(
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    ),
    Event(
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    ),
    Event(
        meetingName = "Developer meeting",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf<String>("Python", "Junior","Moscow"),
        avatarUrl = "",
        active = true
    )
)