package com.example.composeprotject.data.mock

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.domain.model.CommunityDetails
import com.example.domain.model.CommunityMeetings

class MockCommunityDetails {

    fun communityDetails() = listOf<com.example.domain.model.CommunityDetails>(
        com.example.domain.model.CommunityDetails(
            communityId = 0,
            nameGroup = "Designa",
            communityDescription = LoremIpsum(300).values.first(),
            communityMeetings = listOf(
                com.example.domain.model.CommunityMeetings(
                    communityId = 0,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = true
                )
            )
        ),
        com.example.domain.model.CommunityDetails(
            communityId = 1,
            nameGroup = "Designa",
            communityDescription = LoremIpsum(300).values.first(),
            communityMeetings = listOf(
                com.example.domain.model.CommunityMeetings(
                    communityId = 1,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = true
                ),
                com.example.domain.model.CommunityMeetings(
                    communityId = 1,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = false
                )
            )
        ), com.example.domain.model.CommunityDetails(
            communityId = 2,
            nameGroup = "Designa",
            communityDescription = LoremIpsum(300).values.first(),
            communityMeetings = listOf(
                com.example.domain.model.CommunityMeetings(
                    communityId = 2,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = true
                )
            )
        ), com.example.domain.model.CommunityDetails(
            communityId = 3,
            nameGroup = "Designa",
            communityDescription = LoremIpsum(300).values.first(),
            communityMeetings = listOf(
                com.example.domain.model.CommunityMeetings(
                    communityId = 3,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = false
                ),
                com.example.domain.model.CommunityMeetings(
                    communityId = 3,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = true
                )
            )
        )

    )
}