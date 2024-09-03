package com.example.composeprotject.model.details

import com.example.domain.model.interest.Interest

data class MeetingParticipants(
    val name: String,
    val interests: List<Interest>,
    val avatarUrl: String?
)
