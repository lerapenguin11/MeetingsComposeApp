package com.example.data.mock

import com.example.domain.model.CommunityDetails
import com.example.domain.model.CommunityMeetings


class MockCommunityDetails {

    fun communityDetails() = MutableList(10) {
        CommunityDetails(
            communityId = 0,
            nameGroup = "Designa",
            communityDescription = loremIpsum(),
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

    private fun loremIpsum(): String {
        return """
Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
It has survived not only five centuries, but also the leap into electronic typesetting, 
remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset 
sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like 
Aldus PageMaker including versions of Lorem Ipsum.
""".trimIndent()
    }
}