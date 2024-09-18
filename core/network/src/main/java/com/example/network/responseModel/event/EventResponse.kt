package com.example.network.responseModel.event

class EventResponse : ArrayList<EventResponseItem>()

data class EventResponseItem(
    val tags: List<Category>,
    val id: Int,
    val image: String,
    val location: Location?, //TODO
    val startDate: String, //TODO LONg
    val title: String
)