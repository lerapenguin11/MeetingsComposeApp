package com.example.data.mappers

import com.example.data.responseModel.event.EventResponseItem
import com.example.domain.model.event.Meeting
import com.example.domain.model.interest.Category

class EventsMapper {

    fun eventResponseToMeeting(item: EventResponseItem): Meeting {
        return Meeting(
            id = item.id,
            avatarUrl = item.image,
            title = item.title,
            categories = item.categories.map { Category(it.id, it.title) },
            shortAddress = item.location.address.plain.short,
            startDate = item.startDate
        )
    }
}