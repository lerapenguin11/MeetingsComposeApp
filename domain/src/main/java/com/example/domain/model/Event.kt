package com.example.domain.model

data class Event(
    val eventId: Int,
    val meetingName: String,
    val dateLocation: String,
    val tags: List<String>,
    val avatarUrl: String?,
    val active: Boolean
)