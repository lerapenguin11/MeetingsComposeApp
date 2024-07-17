package com.example.composeprotject.domain.repository

import com.example.composeprotject.domain.model.EventDetails

interface EventDetailsRepository {

    fun getEventById(eventId : Int) : EventDetails
}