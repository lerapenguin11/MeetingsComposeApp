package com.example.data.mock

import com.example.domain.model.EventDetails


class MockEventDetailsData {

    fun eventList() = listOf(
        EventDetails(
            eventId = 0,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = loremIpsum(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 1,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = loremIpsum(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 2,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = loremIpsum(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 3,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = loremIpsum(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 4,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = loremIpsum(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 5,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = loremIpsum(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        ),
        EventDetails(
            eventId = 6,
            meetingName = "Developer meeting 1",
            dateLocation = "13.09.2024 — Москва",
            tags = listOf("Python", "Junior", "Moscow"),
            avatarUrl = "",
            active = true,
            eventDescription = loremIpsum(),
            address = "ул. Громова, 4",
            avatarList = avatarList()
        )
    )

    private fun avatarList() = listOf(
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg",
        "https://amiel.club/uploads/posts/2022-03/1647762904_9-amiel-club-p-kartinki-litsa-cheloveka-10.jpg"
    )

    private fun loremIpsum() : String{
        return "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with " +
                "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    }
}