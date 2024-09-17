package com.example.network.responseModel.eventDetails

data class EventDetailsResponse(
    val categories: List<Category>,
    val description: String,
    val image: String,
    val isParticipating: Boolean,
    val location: Location,
    val organizers: List<Organizer>,
    val participants: Participants,
    val participantsCapacity: Int,
    val presenters: List<Presenter>,
    val startDate: Long,
    val status: String,
    val title: String
)