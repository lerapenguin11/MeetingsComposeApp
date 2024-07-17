package com.example.composeprotject.data.mock

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.composeprotject.domain.model.CommunityDetails
import com.example.composeprotject.domain.model.CommunityMeetings

class MockCommunityDetails {

    fun communityDetails() = listOf<CommunityDetails>(
        CommunityDetails(
            communityId = 0,
            nameGroup = "Designa",
            communityDescription = LoremIpsum(300).values.first(),
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
        ),
        CommunityDetails(
            communityId = 1,
            nameGroup = "Designa",
            communityDescription = LoremIpsum(300).values.first(),
            communityMeetings = listOf(
                CommunityMeetings(
                    communityId = 1,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = true
                ),
                CommunityMeetings(
                    communityId = 1,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = false
                )
            )
        ),CommunityDetails(
            communityId = 2,
            nameGroup = "Designa",
            communityDescription = LoremIpsum(300).values.first(),
            communityMeetings = listOf(
                CommunityMeetings(
                    communityId = 2,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = true
                )
            )
        ), CommunityDetails(
            communityId = 3,
            nameGroup = "Designa",
            communityDescription = LoremIpsum(300).values.first(),
            communityMeetings = listOf(
                CommunityMeetings(
                    communityId = 3,
                    meetingName = "Developer meeting",
                    dateLocation = "13.09.2024 — Москва",
                    tags = listOf<String>("Python", "Junior", "Moscow"),
                    avatarUrl = "",
                    activeEvent = false
                ),
                CommunityMeetings(
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