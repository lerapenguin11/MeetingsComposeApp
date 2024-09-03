package com.example.domain.old.repository

import com.example.domain.old.model.Event

interface EventRepository {

    fun getActiveEvent() : List<Event>
    fun getAllEvent() : List<Event>
    fun getMyInactiveEvent() : List<Event>
    fun getMyActiveEvent() : List<Event>
}