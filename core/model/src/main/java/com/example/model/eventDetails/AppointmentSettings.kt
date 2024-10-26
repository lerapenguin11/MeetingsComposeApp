package com.example.model.eventDetails

data class AppointmentSettings(
    val eventId: Int,
    val autToken: String?,
    val isParticipatingMeeting: Boolean
)