package com.example.domain.old.repository

import com.example.domain.old.model.EventDetails

interface EventDetailsRepository {

    fun getEventById(eventId : Int) : EventDetails
}