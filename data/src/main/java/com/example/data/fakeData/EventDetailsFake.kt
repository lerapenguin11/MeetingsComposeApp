package com.example.data.fakeData

import com.example.data.responseModel.eventDetails.Category
import com.example.data.responseModel.eventDetails.Data
import com.example.data.responseModel.eventDetails.EventDetailsResponse
import com.example.data.responseModel.eventDetails.Organizer
import com.example.data.responseModel.eventDetails.Participants
import com.example.data.responseModel.eventDetails.Presenter
import java.util.Date

fun eventDetailsFake(): List<EventDetailsResponse> {
    val eventDetailsResponses = mutableListOf<EventDetailsResponse>()
    for (i in 1..30) {
        eventDetailsResponses.add(
            EventDetailsResponse(
                categories = listOf(
                    Category(id = i, title = "Category $i")
                ),
                description = "Description for Event $i",
                image = "https://example.com/image$i.jpg",
                isParticipating = i % 2 == 0,
                location = com.example.data.responseModel.eventDetails.Location(
                    address = com.example.data.responseModel.eventDetails.Address(
                        plain = com.example.data.responseModel.eventDetails.Plain(
                            full = "Address $i",
                            short = "Short Address $i"
                        )
                    ),
                    coordinates = com.example.data.responseModel.eventDetails.Coordinates(
                        lat = 51.5074 + i * 0.01,
                        lon = 0.1278 + i * 0.01
                    )
                ),
                organizers = listOf(
                    Organizer(
                        bio = "Organizer Bio $i",
                        id = i,
                        image = "https://example.com/organizer$i.jpg",
                        name = "Organizer $i"
                    )
                ),
                participants = Participants(
                    `data` = listOf(
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                        Data(id = i, image = "https://example.com/participant$i.jpg"),
                    ),
                    total = 100 + i
                ),
                participantsCapacity = 200 + i,
                presenters = listOf(
                    Presenter(
                        bio = "Presenter Bio $i",
                        name = "Presenter $i"
                    )
                ),
                startDate = Date().time + i * 86400000L, // 1 day interval
                status = "active",
                title = "Event $i"
            )
        )
    }

    return eventDetailsResponses
}