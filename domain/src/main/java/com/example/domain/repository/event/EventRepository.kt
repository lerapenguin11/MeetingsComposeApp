package com.example.domain.repository.event

import com.example.domain.model.event.EventListType
import com.example.domain.model.event.Meeting
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    fun getEvents(
        eventType: EventListType,
        userInterests: List<Int>?,
        authToken: String?
    ): Flow<List<Meeting>>
}