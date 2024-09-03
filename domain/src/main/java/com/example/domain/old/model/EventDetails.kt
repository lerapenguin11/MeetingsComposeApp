package com.example.domain.old.model

data class EventDetails(
    val eventId: Int,
    val meetingName: String,
    val dateLocation: String,
    val tags: List<String>,
    val avatarUrl: String,
    val active: Boolean,
    val eventDescription : String,
    val address : String,
    val avatarList : List<String>
)
