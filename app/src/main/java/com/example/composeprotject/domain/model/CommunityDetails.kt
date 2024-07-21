package com.example.composeprotject.domain.model

data class CommunityDetails(
    val communityId: Int,
    val nameGroup: String,
    val communityDescription : String,
    val communityMeetings : List<CommunityMeetings>
)
