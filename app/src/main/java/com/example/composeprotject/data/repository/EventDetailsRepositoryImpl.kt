package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockEventDetailsData
import com.example.composeprotject.domain.model.EventDetails
import com.example.composeprotject.domain.repository.EventDetailsRepository

class EventDetailsRepositoryImpl(private val mock : MockEventDetailsData) : EventDetailsRepository{

    override fun getEventById(eventId: Int): EventDetails {
        return mock.eventList().last { eventId == it.eventId } //TODO
    }
}