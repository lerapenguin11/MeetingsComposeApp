package com.example.composeprotject.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprotject.screen.state.AllEventsScreenState
import com.example.composeprotject.screen.state.SubscriptionCapabilityStatus
import com.example.composeprotject.ui.component.card.CommunityCard
import com.example.composeprotject.ui.component.card.EventCardFillMaxWidth
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.domain.model.community.Community
import com.example.domain.model.event.Meeting

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AllEventsScreen(
    modifier: Modifier = Modifier
) {
    val state = AllEventsScreenState.ALL_COMMUNITIES
    val communities = listOf(
        Community(
            id = 0,
            avatarUrl = null,
            title = "TEST",
            statusSubscription = true
        ),
        Community(
            id = 1,
            avatarUrl = null,
            title = "TEST",
            statusSubscription = true
        ),
        Community(
            id = 2,
            avatarUrl = null,
            title = "TEST",
            statusSubscription = true
        )
    )

    val events = listOf(
        Meeting(
            id = 0,
            title = "amogus",
            avatarUrl = null,
            categories = emptyList(),
            shortAddress = "dfdd",
            startDate = 1212121212121
        ),
        Meeting(
            id = 1,
            title = "amogus",
            avatarUrl = null,
            categories = emptyList(),
            shortAddress = "dfdd",
            startDate = 1212121212121
        ),
        Meeting(
            id = 2,
            title = "amogus",
            avatarUrl = null,
            categories = emptyList(),
            shortAddress = "dfdd",
            startDate = 1212121212121
        )
    )

    when (state) {
        AllEventsScreenState.ALL_COMMUNITIES -> {
            AllCommunities(contentPadding = PaddingValues(), communities = communities)
        }

        AllEventsScreenState.ALL_RELEVANT_MEETINGS, AllEventsScreenState.ALL_UPCOMING_MEETINGS -> {
            AllEvents(contentPadding = PaddingValues(), events = events)
        }
    }
}

@Composable
private fun AllEvents(
    contentPadding: PaddingValues,
    events: List<Meeting>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX16)
    ) {
        item { SpacerHeight(height = MeetTheme.sizes.sizeX32) }
        items(items = events, key = { it.id }) { event ->
            EventCardFillMaxWidth(
                meeting = event
            ) {
                // onClickEvent()
            }
            SpacerHeight(height = MeetTheme.sizes.sizeX10)
        }
    }
}

@Composable
private fun AllCommunities(
    contentPadding: PaddingValues,
    communities: List<Community>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX16),
        contentPadding = PaddingValues(top = MeetTheme.sizes.sizeX32, bottom = 28.dp),
        columns = GridCells.Fixed(count = NUMBER_COLUMNS),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(items = communities, key = { it.id }) { community ->
            CommunityCard(
                state = SubscriptionCapabilityStatus.THERE_SUBSCRIPTION,
                community = community,
                buttonState = community.statusSubscription,
                onClickCard = {
                    // onClickCommunity(community)
                },
                onChangingSubscription = { communityId, statusSubscription ->
                    //onChangingSubscription(communityId, statusSubscription)
                }
            )
        }
    }
}

private const val NUMBER_COLUMNS = 3