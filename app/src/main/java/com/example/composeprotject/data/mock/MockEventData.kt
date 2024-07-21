package com.example.composeprotject.data.mock

import com.example.composeprotject.domain.model.nav.Event

class MockEventData {

    fun eventList() = arrayListOf<Event>(
        Event(
            eventId = 0,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        ),
        Event(
            eventId = 1,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = false
        ),
        Event(
            eventId = 2,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = false
        ),
        Event(
            eventId = 3,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        ),
        Event(
            eventId = 4,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        ),
        Event(
            eventId = 5,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        ),
        Event(
            eventId = 6,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        )
    )
}