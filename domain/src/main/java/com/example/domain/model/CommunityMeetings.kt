package com.example.domain.model

data class CommunityMeetings(
    val communityId: Int,
    val meetingName: String,
    val dateLocation: String,
    val tags: List<String>,
    val avatarUrl: String,
    val activeEvent: Boolean
)
