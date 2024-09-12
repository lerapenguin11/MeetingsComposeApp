package com.example.domain.model.eventDetails

data class MeetingParticipants(
    val data: List<MeetingsData>,
    val total: Int
)

data class MeetingsData(
    val id: Int,
    val avatarUrl: String?
)
