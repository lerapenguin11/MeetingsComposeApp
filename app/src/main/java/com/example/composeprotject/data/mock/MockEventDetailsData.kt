package com.example.composeprotject.data.mock

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.composeprotject.domain.model.EventDetails


class MockEventDetailsData {

    fun eventList() = listOf<EventDetails>(
        EventDetails(
            eventId = 0,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = LoremIpsum(300).values.first(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 1,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = LoremIpsum(300).values.first(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 2,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = LoremIpsum(300).values.first(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 3,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = LoremIpsum(300).values.first(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 4,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = LoremIpsum(300).values.first(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 5,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = LoremIpsum(300).values.first(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 6,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf<String>("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = LoremIpsum(300).values.first(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        )
    )

    fun avatarList() = listOf(
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg"
    )
}