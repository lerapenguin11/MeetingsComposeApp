package com.example.composeprotject.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeprotject.model.EventInfoShort
import com.example.composeprotject.ui.component.button.BottomActionBar
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.card.EventViewAllCard
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.image.EventDetailsImage
import com.example.composeprotject.ui.component.person.PersonImage
import com.example.composeprotject.ui.component.person.PersonRow
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.spacer.SpacerWidth
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.component.utils.eventDetailsDate
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.utils.lineBreakInAddress
import com.example.composeprotject.viewModel.EventDetailsViewModel
import com.example.domain.model.event.Meeting
import com.example.domain.model.eventDetails.MeetingOrganizer
import com.example.domain.model.eventDetails.MeetingStatus
import com.example.domain.model.eventDetails.MeetingsData
import com.example.domain.model.interest.Category
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventDetailsScreen(
    eventId: Int,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    eventDetailsViewModel: EventDetailsViewModel = koinViewModel(),
    onClickMorePeople: (Int) -> Unit,
    onClickOrganizer: (MeetingOrganizer) -> Unit,
    onClickEvent: (Meeting) -> Unit,
    onMeetingRegistrationCheckIn: (EventInfoShort) -> Unit
) {
    LaunchedEffect(Unit) {
        eventDetailsViewModel.loadEventDetailsInfo(eventId = eventId)
    }

    val fullEventInfo by eventDetailsViewModel.getEventDetailsInfo().collectAsStateWithLifecycle()

    val status = MeetingStatus.ACTIVE

    Column(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = MeetTheme.sizes.sizeX16)
                .weight(1f)
        ) {
            item {
                fullEventInfo?.eventDetails?.let { item ->
                    SpacerHeight(height = MeetTheme.sizes.sizeX8)
                    CommonInfo(
                        avatarUrl = item.image,
                        title = item.title,
                        startDate = item.startDate,
                        shortMeetingAddress = item.location.meetingAddress.short,
                        categories = item.categories,
                        description = item.description,
                        status = item.status
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX32)
                    LeaderInfo(
                        name = item.presenters.get(0).name,
                        bio = item.presenters.get(0).bio,
                        avatarUrl = item.presenters.get(0).avatar,
                        placeholder = CommonDrawables.ic_community_placeholder
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX32)
                    LocationInfo(
                        short = item.location.meetingAddress.short,
                        full = item.location.meetingAddress.full,
                        metro = item.location.meetingAddress.metro
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX32)
                    PeopleAtMeetings(
                        meetingStatus = status,
                        avatarList = item.participants.data,
                        onClickMorePeople = {
                            onClickMorePeople(eventId)
                        }
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX32)
                    OrganizerInfo(
                        name = item.organizers.name,
                        bio = item.organizers.bio,
                        placeholder = CommonDrawables.ic_community_placeholder,
                        avatarUrl = item.organizers.image,
                        onClickOrganizer = {
                            onClickOrganizer(
                                MeetingOrganizer(
                                    id = item.organizers.id,
                                    name = item.organizers.name,
                                    bio = item.organizers.bio,
                                    image = item.organizers.image
                                )
                            )
                        }
                    )
                    if (!fullEventInfo?.eventsByCommunityId.isNullOrEmpty()) {
                        SpacerHeight(height = MeetTheme.sizes.sizeX32)
                        OtherCommunityMeetings(
                            eventsCommunity = fullEventInfo!!.eventsByCommunityId,
                            onClickEvent = {
                                onClickEvent(it)
                            }
                        )
                    }
                }
            }
            item { SpacerHeight(height = 28.dp) }
        }
        if (fullEventInfo?.eventDetails?.status == MeetingStatus.ACTIVE) {
            fullEventInfo?.eventDetails?.let { meetingDetails ->
                BottomActionBar(
                    buttonText = "Записаться на встречу",
                    descText = "Всего ${meetingDetails.participantsCapacity} мест. Если передумаете — отпишитесь",
                    state = FilledButtonState.ACTIVE_PRIMARY
                ) {
                    makeAnAppointment(
                        token = fullEventInfo?.authToken,
                        eventId = eventId,
                        title = meetingDetails.title,
                        shortMeetingAddress = meetingDetails.location.meetingAddress.short,
                        startDate = meetingDetails.startDate,
                        onMeetingRegistrationCheckIn = {
                            onMeetingRegistrationCheckIn(it)
                        }
                    )
                }
            }
        }
    }
}

private fun makeAnAppointment(
    token: String?,
    eventId: Int,
    title: String,
    shortMeetingAddress: String,
    startDate: Long,
    onMeetingRegistrationCheckIn: (EventInfoShort) -> Unit
) {
    if (token.isNullOrEmpty()) {
        onMeetingRegistrationCheckIn(
            EventInfoShort(
                id = eventId,
                title = title,
                shortAddress = shortMeetingAddress,
                startDate = startDate
            )
        ) //TODO запись на встечу и регистрация
    } else {
        //TODO
    }
}

@Composable
private fun OtherCommunityMeetings(
    eventsCommunity: List<Meeting>,
    onClickEvent: (Meeting) -> Unit
) {
    Text(
        text = stringResource(CommonString.text_other_community_Meetings),
        color = Color.Black,
        style = MeetTheme.typography.interSemiBold24
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX16)
    LazyRow {
        itemsIndexed(eventsCommunity) { index, meeting ->
            if (index < MAX_NUMBER_CARDS_DISPLAYED) {
                EventCard(
                    meeting = meeting,
                    variant = EventCardVariant.MEDIUM
                ) {
                    onClickEvent(meeting)
                }
                SpacerWidth(width = MeetTheme.sizes.sizeX10)
            }
        }
        item {
            if (eventsCommunity.size > MAX_NUMBER_CARDS_DISPLAYED) {
                EventViewAllCard(
                    variant = EventCardVariant.MEDIUM
                ) {/*TODO*/ }
                SpacerWidth(width = MeetTheme.sizes.sizeX16)
            }
        }
    }
}

@Composable
private fun OrganizerInfo(
    placeholder: Int,
    name: String,
    bio: String,
    avatarUrl: String?,
    onClickOrganizer: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Card(
            onClick = { onClickOrganizer() },
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = stringResource(CommonString.text_organizer),
                color = Color.Black,
                style = MeetTheme.typography.interSemiBold24
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX16)
            MeetingOrganizerBlock(
                name = name,
                bio = bio,
                placeholder = placeholder,
                avatarUrl = avatarUrl
            )
        }
    }
}

@Composable
private fun PeopleAtMeetings(
    meetingStatus: MeetingStatus,
    avatarList: List<MeetingsData>,
    onClickMorePeople: () -> Unit,
) {
    when (meetingStatus) {
        MeetingStatus.ACTIVE -> {
            Text(
                text = stringResource(CommonString.text_will_meet_halfway),
                color = Color.Black,
                style = MeetTheme.typography.interSemiBold24
            )
        }

        MeetingStatus.INACTIVE -> {
            Text(
                text = stringResource(CommonString.text_were_at_meeting),
                color = Color.Black,
                style = MeetTheme.typography.interSemiBold24
            )
        }

        MeetingStatus.CANCELLATION -> {
            Text(
                text = stringResource(CommonString.text_could_have_gone),
                color = Color.Black,
                style = MeetTheme.typography.interSemiBold24
            )
        }
    }
    SpacerHeight(height = MeetTheme.sizes.sizeX16)
    PersonRow(
        avatarList = avatarList.map { it.avatarUrl },
        onClickMorePeople = onClickMorePeople
    )
}

@Composable
private fun LocationInfo(
    short: String,
    full: String,
    metro: String,
) {
    Text(
        text = lineBreakInAddress(
            short = short,
            full = full
        ),
        color = Color.Black,
        style = MeetTheme.typography.interSemiBold24
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX2)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = CommonDrawables.ic_metro),
            contentDescription = null
        )
        SpacerWidth(width = MeetTheme.sizes.sizeX4)
        Text(
            text = metro,
            color = Color.Black,
            style = MeetTheme.typography.interMedium14
        )
    }
    SpacerHeight(height = MeetTheme.sizes.sizeX10)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .height(180.dp)
            .fillMaxWidth()
    ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = CommonDrawables.ic_map),
            contentDescription = null
        )
    }
}

@Composable
private fun LeaderInfo(
    name: String,
    bio: String,
    avatarUrl: String?,
    placeholder: Int
) {
    Text(
        text = stringResource(CommonString.text_leader),
        color = Color.Black,
        style = MeetTheme.typography.interSemiBold24
    )

    SpacerHeight(height = MeetTheme.sizes.sizeX16)
    MeetingOrganizerBlock(
        name = name,
        bio = bio,
        avatarUrl = avatarUrl,
        placeholder = placeholder
    )
}

@Composable
private fun MeetingOrganizerBlock(
    name: String,
    bio: String,
    placeholder: Int,
    avatarUrl: String?,
    modifier: Modifier = Modifier
) {
    Row {
        Column(
            modifier = modifier
                .weight(5f, true)
        ) {
            Text(
                text = name,
                color = Color.Black,
                style = MeetTheme.typography.interSemiBold14
            )
            Text(
                text = bio,
                color = Color.Black,
                style = MeetTheme.typography.interMedium14,
                maxLines = DESCRIPTION_MAX_LINE,
                overflow = TextOverflow.Ellipsis
            )
        }
        SpacerWidth(width = MeetTheme.sizes.sizeX10)
        PersonImage(
            placeholderImage = placeholder,
            avatarUrl = avatarUrl,
            size = 104.dp
        )
    }
}

@Composable
private fun EventChipBlock(categories: List<Category>) {
    FlexRow(
        horizontalGap = MeetTheme.sizes.sizeX8,
        verticalGap = MeetTheme.sizes.sizeX8,
        alignment = Alignment.Start
    ) {
        repeat(categories.size) { index ->
            Chip(
                text = categories[index].title,
                chipSize = ChipSize.MEDIUM,
                chipColors = ChipSelect.FALSE,
                chipClick = ChipClick.NOT_ON_CLICK,
                onClick = {}
            )
        }
    }
}

@Composable
private fun CommonInfo(
    categories: List<Category>,
    avatarUrl: String?,
    title: String,
    startDate: Long,
    shortMeetingAddress: String,
    description: String,
    modifier: Modifier = Modifier,
    status: MeetingStatus
) {
    Column(modifier = modifier) {
        EventDetailsImage(
            height = 267.dp,
            avatarUrl = avatarUrl,
            placeholderImage = CommonDrawables.ic_placeholder_details
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        Text(
            text = title,
            color = Color.Black,
            style = MeetTheme.typography.interBold34
        )
        if (status == MeetingStatus.INACTIVE) {
            Text(
                text = stringResource(CommonString.text_meeting_over),
                color = MeetTheme.colors.darkGray,
                style = MeetTheme.typography.interMedium14
            )
        }
        SpacerHeight(height = MeetTheme.sizes.sizeX2)
        Text(
            text = "${eventDetailsDate(timestamp = startDate)} · $shortMeetingAddress",
            color = MeetTheme.colors.darkGray,
            style = MeetTheme.typography.interMedium14
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX8)
        EventChipBlock(categories = categories)
        SpacerHeight(height = MeetTheme.sizes.sizeX32)
        ParagraphSplit(description)
    }
}

@Composable
private fun ParagraphSplit(description: String) {
    description.split("\n").forEach { paragraph ->
        Text(
            text = paragraph,
            modifier = Modifier.padding(vertical = 4.dp),
            color = Color.Black,
            style = MeetTheme.typography.interMedium14
        )
    }
}

private const val DESCRIPTION_MAX_LINE = 5
private const val MAX_NUMBER_CARDS_DISPLAYED = 5
