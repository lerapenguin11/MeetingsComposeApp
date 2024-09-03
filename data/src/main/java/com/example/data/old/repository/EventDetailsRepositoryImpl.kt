package com.example.data.old.repository

import com.example.data.old.mock.MockEventDetailsData
import com.example.domain.old.model.EventDetails
import com.example.domain.old.repository.EventDetailsRepository

internal class EventDetailsRepositoryImpl(private val mock : MockEventDetailsData) :
    EventDetailsRepository {

    override fun getEventById(eventId: Int): EventDetails {
        return mock.eventList().last { eventId == it.eventId } //TODO
    }
}