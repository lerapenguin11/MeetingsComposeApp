package com.example.model.allEvents

data class RelevantEventsQueryParams(
    val userInterests: List<Int>?,
    val authToken: String?
)
