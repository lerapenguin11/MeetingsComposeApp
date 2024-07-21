package com.example.data.repository

import com.example.data.mock.MockEventData
import com.example.domain.model.Event
import com.example.domain.repository.EventRepository

internal class EventRepositoryImpl(private val mock : MockEventData) :
    EventRepository {
    override fun getActiveEvent(): List<Event> {
        return mock.eventList().filter { it.active } //TODO
    }

    override fun getAllEvent(): List<Event> {
        return mock.eventList() //TODO
    }

    override fun getMyInactiveEvent(): List<Event> {
       return mock.eventList().filter { !it.active } //TODO
    }

    override fun getMyActiveEvent(): List<Event> {
        return mock.eventList().filter { it.active }//TODO
    }
}