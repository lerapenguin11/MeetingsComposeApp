package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockEventData
import com.example.composeprotject.domain.model.nav.Event
import com.example.composeprotject.domain.repository.EventRepository

class EventRepositoryImpl(private val mock : MockEventData) : EventRepository{
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