package com.example.model.eventDetails

data class MeetingDetails(
    val status: MeetingStatus,
    val title: String,
    val description: String,
    val image: String?,
    val startDate: Long,
    val categories: List<com.example.model.interest.Category>,
    val presenters: List<MeetingPresenter>,
    val organizers: MeetingOrganizer,
    val participants: MeetingParticipants,
    val participantsCapacity: Int,
    val location: MeetingLocation,
    val isParticipating: Boolean
)