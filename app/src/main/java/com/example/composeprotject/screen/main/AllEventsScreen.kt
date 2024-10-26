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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeprotject.screen.state.AllEventsScreenState
import com.example.composeprotject.screen.state.SubscriptionCapabilityStatus
import com.example.composeprotject.ui.component.card.CommunityCard
import com.example.composeprotject.ui.component.card.EventCardFillMaxWidth
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.AllEventsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllEventsScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    allEventsViewModel: AllEventsViewModel = koinViewModel(),
    screenState: Int
) {
    val communities = listOf(
        com.example.model.community.Community(
            id = 0,
            avatarUrl = null,
            title = "TEST",
            statusSubscription = true
        ),
        com.example.model.community.Community(
            id = 1,
            avatarUrl = null,
            title = "TEST",
            statusSubscription = true
        ),
        com.example.model.community.Community(
            id = 2,
            avatarUrl = null,
            title = "TEST",
            statusSubscription = true
        )
    )

    val fullQueryParamLocal by allEventsViewModel.getFullQueryParamLocalFlow()
        .collectAsStateWithLifecycle()
    val relevantEvents by allEventsViewModel.getEventsByCategoryFlow().collectAsStateWithLifecycle()

    LaunchedEffect(fullQueryParamLocal) {
        when (screenState) {
            AllEventsScreenState.ALL_RELEVANT_MEETINGS.res -> {
                allEventsViewModel.loadData(
                    queryParam = com.example.model.allEvents.RelevantEventsQueryParams(
                        userInterests = fullQueryParamLocal.userInterests,
                        authToken = fullQueryParamLocal.authToken
                    )
                )
            }

            AllEventsScreenState.ALL_COMMUNITIES.res -> {}
            AllEventsScreenState.ALL_UPCOMING_MEETINGS.res -> {}
        }
    }

    when (screenState) {
        AllEventsScreenState.ALL_COMMUNITIES.res -> {
            AllCommunities(contentPadding = contentPadding, communities = communities)
        }

        AllEventsScreenState.ALL_RELEVANT_MEETINGS.res -> {
            AllEvents(contentPadding = contentPadding, events = relevantEvents)
        }

        AllEventsScreenState.ALL_UPCOMING_MEETINGS.res -> {
            AllEvents(contentPadding = contentPadding, events = emptyList())
        }
    }
}

@Composable
private fun AllEvents(
    contentPadding: PaddingValues,
    events: List<com.example.model.event.Meeting>,
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
    communities: List<com.example.model.community.Community>,
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