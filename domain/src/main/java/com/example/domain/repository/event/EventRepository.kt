package com.example.domain.repository.event

import com.example.result.ResultData
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    fun getEventsByUserInterest(
        eventType: com.example.model.event.EventListType,
        userInterests: List<Int>?,
        authToken: String?
    ): Flow<List<com.example.model.event.Meeting>>

    fun getEventsClosest(
        eventType: com.example.model.event.EventListType,
        userInterests: List<Int>?,
        city: String?,
        authToken: String?
    ): Flow<List<com.example.model.event.Meeting>>

    fun getFilteredEventsByCategory(
        filterParam: List<Int>
    ): Flow<List<com.example.model.event.Meeting>>

    fun getEventDetails(params: com.example.model.eventDetails.EventDetailsParams): Flow<com.example.model.eventDetails.MeetingDetails>

    fun getEventsByCommunityId(communityId: Int): Flow<List<com.example.model.event.Meeting>>

    fun makeAnAppointment(params: com.example.model.eventDetails.AppointmentSettings): Flow<ResultData<Boolean>>

    fun skipMeetings(params: com.example.model.eventDetails.AppointmentSettings): Flow<ResultData<Boolean>>
}