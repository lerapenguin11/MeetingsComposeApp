package com.example.domain.repository.event

import com.example.domain.model.event.EventListType
import com.example.domain.model.event.Meeting
import com.example.domain.model.eventDetails.MeetingDetails
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    fun getEventsByUserInterest(
        eventType: EventListType,
        userInterests: List<Int>?,
        authToken: String?
    ): Flow<List<Meeting>>

    fun getEventsClosest(
        eventType: EventListType,
        userInterests: List<Int>?,
        city: String?,
        authToken: String?
    ): Flow<List<Meeting>>

    fun getFilteredEventsByCategory(
        filterParam: List<Int>
    ): Flow<List<Meeting>>

    fun getEventDetails(eventId: Int): Flow<MeetingDetails>

    fun getEventsByCommunityId(communityId: Int): Flow<List<Meeting>>
}