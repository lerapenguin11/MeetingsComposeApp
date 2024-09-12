package com.example.data.responseModel.event

class EventResponse : ArrayList<EventResponseItem>()

data class EventResponseItem(
    val categories: List<Category>,
    val id: Int,
    val image: String,
    val location: Location,
    val startDate: Long,
    val title: String
)