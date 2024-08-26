package com.example.composeprotject.screen.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.composeprotject.R
import com.example.composeprotject.model.community.Community
import com.example.composeprotject.model.meeting.Category
import com.example.composeprotject.model.meeting.Meeting
import com.example.composeprotject.ui.component.card.CommunityCard
import com.example.composeprotject.ui.component.card.CommunityViewAllCard
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.card.EventViewAllCard
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.theme.MeetTheme
import kotlin.random.Random
import kotlin.random.nextUInt

@Composable
fun HomeScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val textSpecialist = "тестировщиков"
    Column(
        modifier = modifier
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX20))
        BigEventsRow(events = events())
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX32))
        Text(
            modifier = Modifier.padding(start = MeetTheme.sizes.sizeX16),
            text = stringResource(R.string.text_upcoming_meetings),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        SmallEventsRow(events = events())
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX32))
        Text(
            modifier = Modifier.padding(
                start = MeetTheme.sizes.sizeX16,
                end = MeetTheme.sizes.sizeX16
            ),
            text = "${stringResource(R.string.text_communities_for)} ${textSpecialist}",
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        CommunityRow(communities = communities())
    }
}

@Composable
fun CommunityRow(
    communities: List<Community>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = MeetTheme.sizes.sizeX16),
        horizontalArrangement = Arrangement.spacedBy(MeetTheme.sizes.sizeX10)
    ) {
        communities.forEachIndexed { index, community ->
            if (index < MAX_NUMBER_CARDS_DISPLAYED) {
                CommunityCard(
                    community = community,
                    buttonState = SubscribeButtonState.NOT_SUBSCRIBED_COMMUNITY
                ) {
                    //TODO
                }
            }
        }
        if (communities.size > MAX_NUMBER_CARDS_DISPLAYED) {
            CommunityViewAllCard {/*TODO*/ }
            Spacer(modifier = Modifier.width(MeetTheme.sizes.sizeX4))
        }
    }
}

@Composable
fun SmallEventsRow(
    events: List<Meeting>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = MeetTheme.sizes.sizeX16),
        horizontalArrangement = Arrangement.spacedBy(MeetTheme.sizes.sizeX10)
    ) {
        events.forEachIndexed { index, meeting ->
            if (index < MAX_NUMBER_CARDS_DISPLAYED) {
                EventCard(
                    meeting = meeting,
                    variant = EventCardVariant.SMALL
                ) {
                    /*TODO*/
                }
            }
        }
        if (events().size > MAX_NUMBER_CARDS_DISPLAYED) {
            EventViewAllCard(
                variant = EventCardVariant.SMALL
            ) {/*TODO*/ }
            Spacer(modifier = Modifier.width(MeetTheme.sizes.sizeX4))
        }
    }
}

@Composable
fun BigEventsRow(
    events: List<Meeting>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = MeetTheme.sizes.sizeX16),
        horizontalArrangement = Arrangement.spacedBy(MeetTheme.sizes.sizeX10)
    ) {
        events.forEachIndexed { index, meeting ->
            if (index < MAX_NUMBER_CARDS_DISPLAYED) {
                EventCard(
                    meeting = meeting,
                    variant = EventCardVariant.BIG
                ) {
                    /*TODO*/
                }
            }
        }
        if (events().size > MAX_NUMBER_CARDS_DISPLAYED) {
            EventViewAllCard(
                variant = EventCardVariant.BIG
            ) {/*TODO*/ }
            Spacer(modifier = Modifier.width(MeetTheme.sizes.sizeX4))
        }
    }
}

private const val MAX_NUMBER_CARDS_DISPLAYED = 5

private fun communities(): List<Community> {
    val communityList = List((10..15).random()) {
        Community(
            id = Random.nextUInt().toInt(),
            title = "Супер тестировщики",
            avatarUrl = null
        )
    }
    return communityList
}

private fun events(): List<Meeting> {
    val eventList = List((10..15).random()) {
        Meeting(
            id = Random.nextUInt().toInt(),
            title = "QA Talks — Global tech forum",
            categories = listOf(
                Category(id = 0, "Маркетинг"),
                Category(id = 1, "Бизнес")
            ),
            avatarUrl = null,
            shortAddress = "Невский проспект, 11",
            startDate = 1724594610
        )
    }
    return eventList
}