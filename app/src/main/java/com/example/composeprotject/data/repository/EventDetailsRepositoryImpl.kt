package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockEventDetailsData
import com.example.domain.model.EventDetails
import com.example.domain.repository.EventDetailsRepository

class EventDetailsRepositoryImpl(private val mock : MockEventDetailsData) :
    com.example.domain.repository.EventDetailsRepository {

    override fun getEventById(eventId: Int): com.example.domain.model.EventDetails {
        return mock.eventList().last { eventId == it.eventId } //TODO
    }
}