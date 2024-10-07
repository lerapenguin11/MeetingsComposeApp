package com.example.data.fakeData

import com.example.network.responseModel.eventDetails.Category
import com.example.network.responseModel.eventDetails.EventDetailsResponse
import com.example.network.responseModel.eventDetails.Organizer
import java.util.Date

fun eventDetailsFake(): List<EventDetailsResponse> {
    val eventDetailsResponses = mutableListOf<EventDetailsResponse>()
    for (i in 1..30) {
        eventDetailsResponses.add(
            EventDetailsResponse(
                categories = listOf(
                    Category(
                        id = i,
                        title = "Category $i"
                    )
                ),
                description = "Description for Event $i",
                image = "https://example.com/image$i.jpg",
                isParticipating = i % 2 == 0,
                location = com.example.network.responseModel.eventDetails.Location(
                    address = com.example.network.responseModel.eventDetails.Address(
                        plain = com.example.network.responseModel.eventDetails.Plain(
                            full = "Address $i",
                            short = "Short Address $i"
                        )
                    ),
                    coordinates = com.example.network.responseModel.eventDetails.Coordinates(
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
                participants = com.example.network.responseModel.eventDetails.Participants(
                    `data` = listOf(
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                        com.example.network.responseModel.eventDetails.Data(
                            id = i,
                            image = "https://example.com/participant$i.jpg"
                        ),
                    ),
                    total = 100 + i
                ),
                participantsCapacity = 200 + i,
                presenters = listOf(
                    com.example.network.responseModel.eventDetails.Presenter(
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