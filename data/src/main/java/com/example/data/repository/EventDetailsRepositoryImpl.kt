package com.example.data.repository

import com.example.data.mock.MockEventDetailsData
import com.example.domain.model.EventDetails
import com.example.domain.repository.EventDetailsRepository

internal class EventDetailsRepositoryImpl(private val mock : MockEventDetailsData) :
    EventDetailsRepository {

    override fun getEventById(eventId: Int): EventDetails {
        return mock.eventList().last { eventId == it.eventId } //TODO
    }
}