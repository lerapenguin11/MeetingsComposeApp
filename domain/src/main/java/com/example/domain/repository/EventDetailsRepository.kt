package com.example.domain.repository

import com.example.domain.model.EventDetails

interface EventDetailsRepository {

    fun getEventById(eventId : Int) : EventDetails
}