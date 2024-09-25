package com.example.data.mappers

import android.text.TextUtils
import com.example.domain.model.event.Meeting
import com.example.domain.model.eventDetails.MeetingAddress
import com.example.domain.model.eventDetails.MeetingCoordinates
import com.example.domain.model.eventDetails.MeetingDetails
import com.example.domain.model.eventDetails.MeetingLocation
import com.example.domain.model.eventDetails.MeetingOrganizer
import com.example.domain.model.eventDetails.MeetingParticipants
import com.example.domain.model.eventDetails.MeetingPresenter
import com.example.domain.model.eventDetails.MeetingStatus
import com.example.domain.model.eventDetails.MeetingsData
import com.example.domain.model.interest.Category
import com.example.network.responseModel.event.EventResponseItem
import com.example.network.responseModel.eventDetails.EventDetailsResponse
import com.example.network.responseModel.eventDetails.Location
import com.example.network.responseModel.eventDetails.Participants
import com.example.network.responseModel.eventDetails.Presenter
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class EventsMapper {

    fun eventResponseToMeeting(item: EventResponseItem): Meeting {
        return Meeting(
            id = item.id,
            avatarUrl = item.image,
            title = item.title,
            categories = item.tags.map { Category(it.id, it.title) },
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

    fun eventDetailsResponseToEventDetails(item: EventDetailsResponse): MeetingDetails {
        return MeetingDetails(
            status = determineMeetingStatus(status = item.status),
            title = item.title,
            description = item.description,
            image = item.image,
            startDate = item.startDate,
            participantsCapacity = item.participantsCapacity,
            categories = item.categories.map { categoryResponseToCategory(it) },
            participants = participantsToMeetingParticipants(item.participants),
            organizers = MeetingOrganizer(
                id = item.organizers.get(0).id,
                name = item.organizers.get(0).name,
                bio = item.organizers.get(0).bio,
                image = item.organizers.get(0).image
            ),
            presenters = item.presenters.map { presentersResponseToPresenters(it) },
            location = locationResponseToLocation(loc = item.location)
        )
    }

    private fun locationResponseToLocation(loc: Location): MeetingLocation {
        return MeetingLocation(
            meetingAddress = MeetingAddress(
                short = loc.address.plain.short,
                full = loc.address.plain.full,
                metro = "Приморская"//TODO
            ),
            coordinates = MeetingCoordinates(
                lat = loc.coordinates.lat,
                lon = loc.coordinates.lon
            )
        )
    }

    private fun presentersResponseToPresenters(presenter: Presenter): MeetingPresenter {
        return MeetingPresenter(
            name = presenter.name,
            bio = presenter.bio,
            avatar = "https://р23.навигатор.дети/images/events/cover/ea24c949d5b9ae168f56e990e0c69b38_big.jpg" //TODO
        )
    }

    private fun participantsToMeetingParticipants(participants: Participants): MeetingParticipants {
        return MeetingParticipants(
            data = participants.data.map { MeetingsData(id = it.id, avatarUrl = it.image) },
            total = participants.total
        )
    }

    private fun determineMeetingStatus(status: String): MeetingStatus {
        return when (status) {
            MeetingStatus.ACTIVE.string -> MeetingStatus.ACTIVE
            MeetingStatus.INACTIVE.name -> MeetingStatus.INACTIVE
            else -> {
                MeetingStatus.CANCELLATION
            }
        }
    }

    private fun categoryResponseToCategory(category: com.example.network.responseModel.eventDetails.Category): Category {
        return Category(
            id = category.id,
            title = category.title
        )
    }
}