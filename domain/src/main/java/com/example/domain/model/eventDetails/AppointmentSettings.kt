package com.example.domain.model.eventDetails

data class AppointmentSettings(
    val eventId: Int,
    val autToken: String?,
    val isParticipatingMeeting: Boolean
)