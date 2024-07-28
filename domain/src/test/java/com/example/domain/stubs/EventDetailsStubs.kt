package com.example.domain.stubs

import com.example.domain.model.EventDetails

internal class EventDetailsStubs {

    val eventId = 1

    fun eventDetails() = EventDetails(
        eventId = 0,
        meetingName = "Developer meeting 1",
        dateLocation = "13.09.2024 — Москва",
        tags = listOf("Python", "Junior", "Moscow"),
        avatarUrl = "",
        active = true,
        eventDescription = "",
        address = "ул. Громова, 4",
        avatarList = listOf()
    )
}