package com.example.composeprotject.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.composeprotject.screen.state.AuthState
import com.example.composeprotject.screen.state.SearchState
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
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.utils.checkingUserNoSuchInterest
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.SearchViewModel
import com.example.domain.model.community.Community
import com.example.domain.model.event.Meeting
import com.example.domain.model.interest.Interest
import com.example.domain.usecase.combineUseCase.CombineMainDataScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    contentPadding: PaddingValues,
    searchViewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinViewModel(),
    onClickEvent: (Meeting) -> Unit,
    onClickCommunity: (Community) -> Unit
) {
    val mainStateUI by mainViewModel.getMainStateUIFlow().collectAsStateWithLifecycle()
    val userCategories by mainViewModel.getUserSelectedCategoriesFlow()
        .collectAsStateWithLifecycle()
    val fullInfoMainScreen by mainViewModel.getFullInfoMainScreenFlow()
        .collectAsStateWithLifecycle()
    val currentLocation by mainViewModel.getCurrentLocationFlow().collectAsStateWithLifecycle()
    val authToken by mainViewModel.getAuthTokenFlow().collectAsStateWithLifecycle()

    val searchQuery by searchViewModel.getSearchQuery().collectAsStateWithLifecycle()
    val mainState by searchViewModel.getMainScreenState().collectAsStateWithLifecycle()

    var authState by remember { mutableStateOf(AuthState.NOT_AUTHORIZED) }

    LaunchedEffect(key1 = Unit, key2 = currentLocation, key3 = authToken) {
        mainViewModel.loadEventsByCategory(
            selectedCategory = userCategories.map { it.id },
            city = currentLocation,
            token = authToken
        )

        if (authToken != null) {
            authState = AuthState.AUTHORIZED
        }
    }

    println("LOC: $currentLocation")


    when (mainState) {
        SearchState.MAIN_SEARCH_SCREEN -> {
            MainSearchScreen()
        }

        SearchState.MAIN_DEFAULT_SCREEN -> {
            MainDefault(
                contentPadding = contentPadding,
                mainStateUI = mainStateUI,
                authState = authState,
                fullInfoMainScreen = fullInfoMainScreen,
                onClickEvent = onClickEvent,
                onClickCommunity = onClickCommunity,
                userCategories = userCategories,
                mainViewModel = mainViewModel
            )
        }
    }
}

@Composable
fun MainSearchScreen() {
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
    authState: AuthState
) {

    val textSpecialist = "тестировщиков"

    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (mainStateUI) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp),

                    contentAlignment = Alignment.Center
                ) {
                    CustomProgressBar()
                }
            }
        } else {
            item {
                SpacerHeight(height = MeetTheme.sizes.sizeX20)
                BigEventsRow(
                    events = fullInfoMainScreen.eventsByCategory,
                    onClickEvent = onClickEvent
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX32)
                Text(
                    modifier = Modifier.padding(start = MeetTheme.sizes.sizeX16),
                    text = stringResource(CommonString.text_upcoming_meetings),
                    color = Color.Black,
                    style = MeetTheme.typography.interSemiBold24
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX16)
                SmallEventsRow(
                    events = fullInfoMainScreen.eventsClosest,
                    onClickEvent = onClickEvent
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX32)
                Text(
                    modifier = Modifier.padding(
                        start = MeetTheme.sizes.sizeX16,
                        end = MeetTheme.sizes.sizeX16
                    ),
                    text = "${stringResource(CommonString.text_communities_for)} $textSpecialist",
                    color = Color.Black,
                    style = MeetTheme.typography.interSemiBold24
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX16)
                CommunityRow(
                    state = authState,
                    communities = fullInfoMainScreen.communities,
                    onClickCommunity = onClickCommunity
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX40)
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
            items(items = fullInfoMainScreen.filteredEventsByCategory) { event ->
                Column(modifier = Modifier.padding(horizontal = MeetTheme.sizes.sizeX16)) {
                    FilteredEventByCategoryBlock(event = event) {
                        onClickEvent(event)
                    }
                    SpacerHeight(height = 38.dp)
                }
            }
            item {
                SpacerHeight(height = MeetTheme.sizes.sizeX24)
            }
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
                chipClick = ChipClick.ON_CLICK
            ) {
                onFilteringByCategory(index)
            }
            if (interests.isNotEmpty() && interests.size - 1 == index) {
                Chip(
                    text = stringResource(R.string.text_all_caterories),
                    chipSize = ChipSize.MEDIUM,
                    chipColors = if (userCategories.isEmpty()) ChipSelect.TRUE else ChipSelect.FALSE,
                    chipClick = ChipClick.ON_CLICK
                ) {
                    onFilteringByAllCategories()
                }
            }
        }
    }
}

@Composable
private fun CommunityRow(
    communities: List<Community>,
    onClickCommunity: (Community) -> Unit,
    state: AuthState
) {
    LazyRow {
        item { SpacerWidth(width = MeetTheme.sizes.sizeX16) }
        itemsIndexed(communities) { _, community ->
            CommunityCard(
                state = state,
                community = community,
                buttonState = SubscribeButtonState.NOT_SUBSCRIBED_COMMUNITY
            ) {
                onClickCommunity(community)
            }
            SpacerWidth(width = MeetTheme.sizes.sizeX10)
        }
        item {
            CommunityViewAllCard {/*TODO*/ }
            SpacerWidth(width = MeetTheme.sizes.sizeX16)
        }
    }
}

@Composable
private fun SmallEventsRow(
    events: List<Meeting>,
    onClickEvent: (Meeting) -> Unit
) {
    LazyRow {
        item { SpacerWidth(width = MeetTheme.sizes.sizeX16) }
        itemsIndexed(events) { _, meeting ->
            EventCard(
                meeting = meeting,
                variant = EventCardVariant.SMALL
            ) {
                onClickEvent(meeting)
            }
            SpacerWidth(width = MeetTheme.sizes.sizeX10)
        }
        item {
            EventViewAllCard(
                variant = EventCardVariant.SMALL
            ) {/*TODO*/ }
            SpacerWidth(width = MeetTheme.sizes.sizeX16)
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
        itemsIndexed(events) { _, meeting ->
            EventCard(
                meeting = meeting,
                variant = EventCardVariant.BIG
            ) {
                onClickEvent(meeting)
            }
            SpacerWidth(width = MeetTheme.sizes.sizeX10)
        }
        item {
            EventViewAllCard(
                variant = EventCardVariant.BIG
            ) { /*TODO*/ }
            Spacer(modifier = Modifier.width(MeetTheme.sizes.sizeX16))
        }
    }
}