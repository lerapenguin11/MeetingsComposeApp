package com.example.domain.stubs

import com.example.domain.model.Event

internal class EventsStubs {

    fun events() = MutableList(10) {
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