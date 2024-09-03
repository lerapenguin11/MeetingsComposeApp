package com.example.data.old.mock

import com.example.domain.old.model.Event

class MockEventData {

    fun eventList() = MutableList(10) {
        Event(
            eventId = 0,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true
        )
    }
}