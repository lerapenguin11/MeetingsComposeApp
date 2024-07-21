package com.example.composeprotject.data.repository

import com.example.composeprotject.data.mock.MockEventData
import com.example.domain.model.Event
import com.example.domain.repository.EventRepository

class EventRepositoryImpl(private val mock : MockEventData) :
    com.example.domain.repository.EventRepository {
    override fun getActiveEvent(): List<com.example.domain.model.Event> {
        return mock.eventList().filter { it.active } //TODO
    }

    override fun getAllEvent(): List<com.example.domain.model.Event> {
        return mock.eventList() //TODO
    }

    override fun getMyInactiveEvent(): List<com.example.domain.model.Event> {
       return mock.eventList().filter { !it.active } //TODO
    }

    override fun getMyActiveEvent(): List<com.example.domain.model.Event> {
        return mock.eventList().filter { it.active }//TODO
    }
}