package com.example.composeprotject.model.details

import com.example.composeprotject.model.interest.Category

data class MeetingDetails(
    val id: Int,
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