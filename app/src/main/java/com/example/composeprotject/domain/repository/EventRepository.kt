package com.example.composeprotject.domain.repository

import com.example.composeprotject.domain.model.nav.Event

interface EventRepository {

    fun getActiveEvent() : List<Event>
    fun getAllEvent() : List<Event>
    fun getMyInactiveEvent() : List<Event>
    fun getMyActiveEvent() : List<Event>
}