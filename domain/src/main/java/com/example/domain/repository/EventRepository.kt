package com.example.domain.repository

import com.example.domain.model.Event

interface EventRepository {

    fun getActiveEvent() : List<Event>
    fun getAllEvent() : List<Event>
    fun getMyInactiveEvent() : List<Event>
    fun getMyActiveEvent() : List<Event>
}