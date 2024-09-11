package com.example.domain.model.eventDetails

import com.example.domain.model.interest.Interest

data class MeetingParticipants(
    val name: String,
    val interests: List<Interest>,
    val avatarUrl: String?
)
