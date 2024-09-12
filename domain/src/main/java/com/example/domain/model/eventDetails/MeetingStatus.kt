package com.example.domain.model.eventDetails

enum class MeetingStatus(val string: String) {
    ACTIVE(string = "active"),
    INACTIVE(string = "inactive"),
    CANCELLATION(string = "cancellation")
}