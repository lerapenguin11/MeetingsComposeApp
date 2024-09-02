package com.example.composeprotject.screen.main

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.model.community.Community
import com.example.composeprotject.model.interest.Category
import com.example.composeprotject.model.interest.Interest
import com.example.composeprotject.model.meeting.Meeting
import com.example.composeprotject.ui.component.card.CommunityCard
import com.example.composeprotject.ui.component.card.CommunityViewAllCard
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.card.EventViewAllCard
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.theme.MeetTheme
import kotlin.random.Random
import kotlin.random.nextUInt

@Composable
fun MainScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onClickEvent: (Meeting) -> Unit,
    onClickCommunity: (Community) -> Unit
) {
    val textSpecialist = "тестировщиков"
    Column(
        modifier = modifier
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX20))
        BigEventsRow(events = events(), onClickEvent = onClickEvent)
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX32))
        Text(
            modifier = Modifier.padding(start = MeetTheme.sizes.sizeX16),
            text = stringResource(CommonString.text_upcoming_meetings),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        SmallEventsRow(events = events(), onClickEvent = onClickEvent)
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX32))
        Text(
            modifier = Modifier.padding(
                start = MeetTheme.sizes.sizeX16,
                end = MeetTheme.sizes.sizeX16
            ),
            text = "${stringResource(CommonString.text_communities_for)} ${textSpecialist}",
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        CommunityRow(communities = communities(), onClickCommunity = onClickCommunity)
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX40))
        Text(
            modifier = Modifier.padding(
                start = MeetTheme.sizes.sizeX16,
                end = MeetTheme.sizes.sizeX16
            ),
            text = stringResource(CommonString.text_other_meetings),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX16))
        InterestsChipFlex(interests = interests())





        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX24))
    }
}

@Composable
private fun InterestsChipFlex(
    interests: List<Interest>
) {
    FlexRow(
        horizontalPadding = 16.dp,
        horizontalGap = MeetTheme.sizes.sizeX10,
        verticalGap = MeetTheme.sizes.sizeX10,
        alignment = Alignment.Start
    ) {
        repeat(interests.size) { index ->
            Chip(
                text = interests[index].title,
                chipSize = ChipSize.MEDIUM,
                chipColors = ChipClick.FALSE
            ) {
                //TODO
            }
        }
    }
}

@Composable
private fun CommunityRow(
    communities: List<Community>,
    modifier: Modifier = Modifier,
    onClickCommunity: (Community) -> Unit
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
                    onClickCommunity(community)
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
private fun SmallEventsRow(
    events: List<Meeting>,
    modifier: Modifier = Modifier,
    onClickEvent: (Meeting) -> Unit
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
                    onClickEvent(meeting)
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
private fun BigEventsRow(
    events: List<Meeting>,
    modifier: Modifier = Modifier,
    onClickEvent: (Meeting) -> Unit
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
                    onClickEvent(meeting)
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

private fun interests(): List<Interest> {
    val interestList = listOf(
        Interest(
            id = 0, title = "Дизайн"
        ),
        Interest(
            id = 1, title = "Разработка"
        ),
        Interest(
            id = 2, title = "Продакт менеджмент"
        ),
        Interest(
            id = 3, title = "Проджект менеджмент"
        ),
        Interest(
            id = 4, title = "Backend"
        ),
        Interest(
            id = 5, title = "Frontend"
        ),
        Interest(
            id = 6, title = "Mobile"
        ),
        Interest(
            id = 7, title = "Web"
        ),
        Interest(
            id = 8, title = "Тестирование"
        ),
        Interest(
            id = 9, title = "Продажи"
        ),
        Interest(
            id = 10, title = "Бизнес"
        ),
        Interest(
            id = 11, title = "Маркетинг"
        ),
        Interest(
            id = 12, title = "Безопасность"
        ),
        Interest(
            id = 13, title = "Девопс"
        ),
        Interest(
            id = 14, title = "Аналитика"
        ),
        Interest(
            id = 15, title = "Все категории"
        ),
    )
    return interestList
}

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