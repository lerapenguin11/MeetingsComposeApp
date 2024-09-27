package com.example.composeprotject.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeprotject.R
import com.example.composeprotject.screen.state.SearchState
import com.example.composeprotject.screen.state.SubscriptionCapabilityStatus
import com.example.composeprotject.ui.component.card.CommunityCard
import com.example.composeprotject.ui.component.card.CommunityViewAllCard
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.card.EventCardFillMaxWidth
import com.example.composeprotject.ui.component.card.EventViewAllCard
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.progressBar.CustomProgressBar
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.spacer.SpacerWidth
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.component.topBar.search.SearchBar
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.utils.checkingUserNoSuchInterest
import com.example.composeprotject.viewModel.MainViewModel
import com.example.domain.model.community.Community
import com.example.domain.model.event.Meeting
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.combineUseCase.CombineMainDataScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinViewModel(),
    onGoProfile: () -> Unit,
    onClickEvent: (Meeting) -> Unit,
    onClickCommunity: (Community) -> Unit
) {
    val mainStateUI by mainViewModel.getMainStateUIFlow().collectAsStateWithLifecycle()
    val userCategories by mainViewModel.getUserSelectedCategoriesFlow()
        .collectAsStateWithLifecycle()
    val fullInfoMainScreen by mainViewModel.getFullInfoMainScreenFlow()
        .collectAsStateWithLifecycle()
    val searchQuery by mainViewModel.getSearchQuery().collectAsStateWithLifecycle()
    val mainState by mainViewModel.getMainScreenState().collectAsStateWithLifecycle()
    val fullQueryParamLocal by mainViewModel.getFullQueryParamLocalFlow()
        .collectAsStateWithLifecycle()
    var subscriptionCapabilityStatus by remember { mutableStateOf(SubscriptionCapabilityStatus.WITHOUT_SUBSCRIPTION) } //TODO вынести во viewModel

    LaunchedEffect(
        key1 = userCategories,
        key2 = Unit,
        key3 = fullQueryParamLocal.authToken
    ) {
        mainViewModel.loadEventsByCategory(
            userCategories = fullQueryParamLocal.userInterests,
            selectedCategory = userCategories.map { it.id },
            city = fullQueryParamLocal.city,
            token = fullQueryParamLocal.authToken
        )
        if (fullQueryParamLocal.authToken != null) {
            subscriptionCapabilityStatus = SubscriptionCapabilityStatus.THERE_SUBSCRIPTION
        }
    }
    LaunchedEffect(Unit) {
        mainViewModel.updateCurrentLocation()
    }

    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .systemBarsPadding(),
        topBar = {
            SearchBar(
                isEnabled = true,
                authToken = fullQueryParamLocal.authToken,
                state = InputState.SUCCESS,
                onValueChange = {
                    mainViewModel.searchQueryUpdate(text = it)
                },
                onMainScreenState = {
                    mainViewModel.mainScreenStateUpdate(state = it)
                },
                onGoProfile = {
                    onGoProfile()
                }
            )
        }) { innerPadding ->
        when (mainState) {
            SearchState.MAIN_SEARCH_SCREEN -> {
                MainSearchScreen(innerPadding)
            }

            SearchState.MAIN_DEFAULT_SCREEN -> {
                MainDefault(
                    contentPadding = innerPadding,
                    mainStateUI = mainStateUI,
                    subscriptionCapabilityStatus = subscriptionCapabilityStatus,
                    fullInfoMainScreen = fullInfoMainScreen,
                    onClickEvent = onClickEvent,
                    onClickCommunity = onClickCommunity,
                    userCategories = userCategories,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}

@Composable
fun MainSearchScreen(innerPadding: PaddingValues) {
    LazyColumn {

    }
}

@Composable
fun MainDefault(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    mainStateUI: Boolean,
    fullInfoMainScreen: CombineMainDataScreen,
    onClickEvent: (Meeting) -> Unit,
    onClickCommunity: (Community) -> Unit,
    userCategories: List<Interest>,
    mainViewModel: MainViewModel,
    subscriptionCapabilityStatus: SubscriptionCapabilityStatus
) {
    if (mainStateUI) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CustomProgressBar()
        }
    }
    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            SpacerHeight(height = MeetTheme.sizes.sizeX20)
            BigEventsRow(
                events = fullInfoMainScreen.eventsByCategory,
                onClickEvent = onClickEvent
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX32)
            SmallEventsRow(
                events = fullInfoMainScreen.eventsClosest,
                onClickEvent = onClickEvent
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX32)
            CommunityRow(
                state = subscriptionCapabilityStatus,
                communities = fullInfoMainScreen.communities,
                onClickCommunity = onClickCommunity
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX40)
            InterestsChipFlex(
                interests = fullInfoMainScreen.categoryList,
                userCategories = userCategories,
                onFilteringByAllCategories = {
                    //TODO
                    mainViewModel.clearUserSelectedCategories()
                }
            ) {
                mainViewModel.toggleUserCategory(fullInfoMainScreen.categoryList[it])
            }
            SpacerHeight(height = MeetTheme.sizes.sizeX40)
        }
        items(items = fullInfoMainScreen.filteredEventsByCategory, key = { event ->
            event.id
        }) { event ->
            Box(modifier = Modifier.padding(horizontal = MeetTheme.sizes.sizeX16)) {
                FilteredEventByCategoryBlock(event = event) {
                    onClickEvent(event)
                }
            }
            SpacerHeight(height = 38.dp)
        }
        item {
            SpacerHeight(height = MeetTheme.sizes.sizeX24)
        }
    }
}

@Composable
private fun FilteredEventByCategoryBlock(
    event: Meeting,
    onClickEvent: (Int) -> Unit
) {
    EventCardFillMaxWidth(
        meeting = event
    ) {
        onClickEvent(event.id)
    }
}

@Composable
private fun InterestsChipFlex(
    interests: List<Interest>,
    userCategories: List<Interest>,
    onFilteringByAllCategories: () -> Unit,
    onFilteringByCategory: (Int) -> Unit
) {
    if (interests.isNotEmpty()) {
        Text(
            modifier = Modifier.padding(
                start = MeetTheme.sizes.sizeX16,
                end = MeetTheme.sizes.sizeX16
            ),
            text = stringResource(CommonString.text_other_meetings),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX16)
    }
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
                chipColors = if (checkingUserNoSuchInterest(
                        userInterests = userCategories,
                        id = interests[index].id
                    )
                ) ChipSelect.FALSE else ChipSelect.TRUE,
                chipClick = ChipClick.ON_CLICK,
                onClick = { onFilteringByCategory(index) }
            )
            if (interests.isNotEmpty() && interests.size - 1 == index) {
                Chip(
                    text = stringResource(R.string.text_all_caterories),
                    chipSize = ChipSize.MEDIUM,
                    chipColors = if (userCategories.isEmpty()) ChipSelect.TRUE else ChipSelect.FALSE,
                    chipClick = ChipClick.ON_CLICK,
                    onClick = { onFilteringByAllCategories() }
                )
            }
        }
    }
}

@Composable
private fun CommunityRow(
    communities: List<Community>,
    state: SubscriptionCapabilityStatus,
    onClickCommunity: (Community) -> Unit
) {
    if (communities.isNotEmpty()) {
        Text(
            modifier = Modifier.padding(
                start = MeetTheme.sizes.sizeX16,
                end = MeetTheme.sizes.sizeX16
            ),
            text = "${stringResource(CommonString.text_communities_for)} тестировщиков",
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX16)
    }
    LazyRow {
        item { SpacerWidth(width = MeetTheme.sizes.sizeX16) }
        items(items = communities, key = { community ->
            community.id
        }) { community ->
            CommunityCard(
                state = state,
                community = community,
                buttonState = SubscribeButtonState.NOT_SUBSCRIBED_COMMUNITY //TODO
            ) {
                onClickCommunity(community)
            }
            SpacerWidth(width = MeetTheme.sizes.sizeX10)
        }
        item {
            if (communities.size > MAX_ELEMENT) {
                CommunityViewAllCard {/*TODO*/ }
                SpacerWidth(width = MeetTheme.sizes.sizeX16)
            }
        }
        item {
            if (communities.size <= MAX_ELEMENT) {
                SpacerWidth(width = MeetTheme.sizes.sizeX6)
            }
        }
    }
}

@Composable
private fun SmallEventsRow(
    events: List<Meeting>,
    onClickEvent: (Meeting) -> Unit
) {
    if (events.isNotEmpty()) {
        Text(
            modifier = Modifier.padding(start = MeetTheme.sizes.sizeX16),
            text = stringResource(CommonString.text_upcoming_meetings),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold24
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX16)
    }
    LazyRow {
        item { SpacerWidth(width = MeetTheme.sizes.sizeX16) }
        items(items = events, key = { event ->
            event.id
        }) { meeting ->
            EventCard(
                meeting = meeting,
                variant = EventCardVariant.MEDIUM
            ) {
                onClickEvent(meeting)
            }
            SpacerWidth(width = MeetTheme.sizes.sizeX10)
        }
        item {
            if (events.size > MAX_ELEMENT) {
                EventViewAllCard(
                    variant = EventCardVariant.MEDIUM
                ) {/*TODO*/ }
                SpacerWidth(width = MeetTheme.sizes.sizeX16)
            }
        }
        item {
            if (events.size <= MAX_ELEMENT) {
                SpacerWidth(width = MeetTheme.sizes.sizeX6)
            }
        }
    }
}

@Composable
private fun BigEventsRow(
    events: List<Meeting>,
    onClickEvent: (Meeting) -> Unit
) {
    LazyRow {
        item { SpacerWidth(width = MeetTheme.sizes.sizeX16) }
        items(items = events, key = { event ->
            event.id
        }) { meeting ->
            EventCard(
                meeting = meeting,
                variant = EventCardVariant.BIG
            ) {
                onClickEvent(meeting)
            }
            SpacerWidth(width = MeetTheme.sizes.sizeX10)
        }
        item {
            if (events.size > MAX_ELEMENT) {
                EventViewAllCard(
                    variant = EventCardVariant.BIG
                ) { /*TODO*/ }
                Spacer(modifier = Modifier.width(MeetTheme.sizes.sizeX16))
            }
        }
        item {
            if (events.size <= MAX_ELEMENT) {
                SpacerWidth(width = MeetTheme.sizes.sizeX6)
            }
        }
    }
}

private const val MAX_ELEMENT = 5