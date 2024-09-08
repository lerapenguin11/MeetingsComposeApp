package com.example.domain.model.event

import com.example.domain.model.user.UserCoordinates

data class EventQueryParam(
    val eventType: EventListType?,
    val userInterests: List<Int>?,
    val authToken: String?,
    val city: String?,
    val coordinates: UserCoordinates?
)