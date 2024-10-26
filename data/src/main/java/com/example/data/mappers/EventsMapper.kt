package com.example.data.mappers

import android.text.TextUtils
import com.example.network.responseModel.event.EventResponseItem
import com.example.network.responseModel.eventDetails.EventDetailsResponse
import com.example.network.responseModel.eventDetails.Location
import com.example.network.responseModel.eventDetails.Participants
import com.example.network.responseModel.eventDetails.Presenter
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class EventsMapper {

    fun eventResponseToMeeting(item: EventResponseItem): com.example.model.event.Meeting {
        return com.example.model.event.Meeting(
            id = item.id,
            avatarUrl = item.image,
            title = item.title,
            categories = item.tags.map { com.example.model.interest.Category(it.id, it.title) },
            shortAddress = item.location?.address?.plain?.short.toString(), //TODO toString delete
            startDate = 1726583978 //TODO: item.startDate
        )
    }

    fun typeConvectorListIdToUriId(ids: List<Int>): String? {
        return try {
            URLEncoder.encode(TextUtils.join(",", ids), "utf-8")
        } catch (e: UnsupportedEncodingException) {
            null
        }
    }

    fun eventDetailsResponseToEventDetails(item: EventDetailsResponse): com.example.model.eventDetails.MeetingDetails {
        return com.example.model.eventDetails.MeetingDetails(
            status = determineMeetingStatus(status = item.status),
            title = item.title,
            description = item.description,
            image = item.image,
            startDate = item.startDate,
            participantsCapacity = item.participantsCapacity,
            categories = item.categories.map { categoryResponseToCategory(it) },
            participants = participantsToMeetingParticipants(item.participants),
            organizers = com.example.model.eventDetails.MeetingOrganizer(
                id = item.organizers.get(0).id,
                name = item.organizers.get(0).name,
                bio = item.organizers.get(0).bio,
                image = item.organizers.get(0).image
            ),
            presenters = item.presenters.map { presentersResponseToPresenters(it) },
            location = locationResponseToLocation(loc = item.location),
            isParticipating = item.isParticipating
        )
    }

    private fun locationResponseToLocation(loc: Location): com.example.model.eventDetails.MeetingLocation {
        return com.example.model.eventDetails.MeetingLocation(
            meetingAddress = com.example.model.eventDetails.MeetingAddress(
                short = loc.address.plain.short,
                full = loc.address.plain.full,
                metro = "Приморская"//TODO
            ),
            coordinates = com.example.model.eventDetails.MeetingCoordinates(
                lat = loc.coordinates.lat,
                lon = loc.coordinates.lon
            )
        )
    }

    private fun presentersResponseToPresenters(presenter: Presenter): com.example.model.eventDetails.MeetingPresenter {
        return com.example.model.eventDetails.MeetingPresenter(
            name = presenter.name,
            bio = presenter.bio,
            avatar = "https://р23.навигатор.дети/images/events/cover/ea24c949d5b9ae168f56e990e0c69b38_big.jpg" //TODO
        )
    }

    private fun participantsToMeetingParticipants(participants: Participants): com.example.model.eventDetails.MeetingParticipants {
        return com.example.model.eventDetails.MeetingParticipants(
            data = participants.data.map {
                com.example.model.eventDetails.MeetingsData(
                    id = it.id,
                    avatarUrl = it.image
                )
            },
            total = participants.total
        )
    }

    private fun determineMeetingStatus(status: String): com.example.model.eventDetails.MeetingStatus {
        return when (status) {
            com.example.model.eventDetails.MeetingStatus.ACTIVE.string -> com.example.model.eventDetails.MeetingStatus.ACTIVE
            com.example.model.eventDetails.MeetingStatus.INACTIVE.name -> com.example.model.eventDetails.MeetingStatus.INACTIVE
            else -> {
                com.example.model.eventDetails.MeetingStatus.CANCELLATION
            }
        }
    }

    private fun categoryResponseToCategory(category: com.example.network.responseModel.eventDetails.Category): com.example.model.interest.Category {
        return com.example.model.interest.Category(
            id = category.id,
            title = category.title
        )
    }
}