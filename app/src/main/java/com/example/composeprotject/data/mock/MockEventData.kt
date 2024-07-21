package com.example.composeprotject.data.mock

import com.example.domain.model.Event

class MockEventData {

    fun eventList() = arrayListOf<com.example.domain.model.Event>(
        com.example.domain.model.Event(
            eventId = 0,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        ),
        com.example.domain.model.Event(
            eventId = 1,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = false
        ),
        com.example.domain.model.Event(
            eventId = 2,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = false
        ),
        com.example.domain.model.Event(
            eventId = 3,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        ),
        com.example.domain.model.Event(
            eventId = 4,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        ),
        com.example.domain.model.Event(
            eventId = 5,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        ),
        com.example.domain.model.Event(
            eventId = 6,
            meetingName = "Developer meeting",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        )
    )
}