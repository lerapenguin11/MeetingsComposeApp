package com.example.domain.model.eventDetails

import com.example.domain.model.interest.Category

data class MeetingDetails(
    val status: MeetingStatus,
    val title: String,
    val description: String,
    val image: String?,
    val startDate: Long,
    val categories: List<Category>,
    val presenters: List<MeetingPresenter>,
    val organizers: List<MeetingOrganizer>,
    val participants: List<MeetingParticipants>,
    val participantsCapacity: Int,
    val location: MeetingLocation
)