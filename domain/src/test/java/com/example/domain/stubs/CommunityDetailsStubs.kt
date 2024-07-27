package com.example.domain.stubs

import com.example.domain.model.CommunityDetails
import com.example.domain.model.CommunityMeetings

internal class CommunityDetailsStubs {

    val communityId = 1

    fun communityDetails() = CommunityDetails(
        communityId = 0,
        nameGroup = "Designa",
        communityDescription = "",
        communityMeetings = listOf(
            CommunityMeetings(
                communityId = 0,
                meetingName = "Developer meeting",
                dateLocation = "13.09.2024 — Москва",
                tags = listOf<String>("Python", "Junior", "Moscow"),
                avatarUrl = "",
                activeEvent = true
            )
        )
    )
}