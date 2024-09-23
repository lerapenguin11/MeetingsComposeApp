package com.example.composeprotject.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.card.EventCardFillMaxWidth
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.person.PersonImage
import com.example.composeprotject.ui.component.person.PersonRow
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.spacer.SpacerWidth
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.CommunityDetailsViewModel
import com.example.domain.model.communityDetails.Category
import com.example.domain.model.communityDetails.Data
import com.example.domain.model.event.Meeting
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunityDetailsScreen(
    communityId: Int,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    communityDetailsViewModel: CommunityDetailsViewModel = koinViewModel(),
    onClickMorePeople: (Int) -> Unit,
    onClickEvent: (Meeting) -> Unit
) {
    LaunchedEffect(Unit) {
        communityDetailsViewModel.loadCommunityDetails(communityId = communityId)
    }

    val fullInfoCommunityDetails by communityDetailsViewModel.getCommunityDetails()
        .collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = MeetTheme.sizes.sizeX16)
            ) {
                fullInfoCommunityDetails.communityDetails?.let {
                    SpacerHeight(height = MeetTheme.sizes.sizeX8)
                    CommonInfo(
                        placeholder = CommonDrawables.ic_community_placeholder,
                        avatarUrl = it.image,
                        name = it.title,
                        categories = it.categories
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX26)
                    ActionBlock(
                        buttonText = stringResource(CommonString.text_subscribe),
                        buttonState = FilledButtonState.ACTIVE_PRIMARY
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX32)
                    DescriptionBlock(
                        description = it.description
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX32)
                    SubscribersBlock(
                        avatarUrl = it.members.data,
                        onClickMorePeople = {
                            onClickMorePeople(communityId)
                        }
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX32)
                }
            }
        }
        if (!fullInfoCommunityDetails.eventsByCommunityId.isNullOrEmpty()) {
            item {
                Text(
                    text = stringResource(CommonString.text_meeting),
                    color = Color.Black,
                    style = MeetTheme.typography.interSemiBold24
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX16)
            }
            //TODO: Встречи
            items(items = fullInfoCommunityDetails.eventsByCommunityId) { event ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = MeetTheme.sizes.sizeX16)
                ) {
                    ActiveEventBlock(
                        event = event,
                        onClickEvent = {
                            onClickEvent(event)
                        }
                    )
                }
            }
            //TODO: Встречи
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = MeetTheme.sizes.sizeX16)
                ) {
                    SpacerHeight(height = 22.dp)
                    Text(
                        text = stringResource(R.string.text_past_meetings),
                        color = Color.Black,
                        style = MeetTheme.typography.interBold34
                    )
                    SpacerHeight(height = MeetTheme.sizes.sizeX16)
                }
            }
            //TODO: Прошлые встречи
            item {
                LazyRow {
                    itemsIndexed(items = fullInfoCommunityDetails.eventsByCommunityId) { index, event ->
                        PastMeetingsBlock(
                            index = index,
                            event = event,
                            eventSize = fullInfoCommunityDetails.eventsByCommunityId.size,
                            onClickEvent = {
                                onClickEvent(event)
                            }
                        )
                    }
                }
                SpacerHeight(height = 28.dp)
            }
            //TODO: Прошлые встречи
        }
    }
}

@Composable
private fun ActiveEventBlock(
    event: Meeting,
    onClickEvent: () -> Unit
) {
    EventCardFillMaxWidth(
        meeting = event
    ) {
        onClickEvent()
    }
    SpacerHeight(height = MeetTheme.sizes.sizeX10)
}

@Composable
private fun PastMeetingsBlock(
    index: Int,
    event: Meeting,
    eventSize: Int,
    onClickEvent: () -> Unit
) {
    if (index == 0) {
        SpacerWidth(width = MeetTheme.sizes.sizeX16)
    }
    EventCard(
        meeting = event,
        variant = EventCardVariant.MEDIUM
    ) {
        onClickEvent()
    }
    SpacerWidth(width = MeetTheme.sizes.sizeX10)
    if (index == eventSize - 1) {
        SpacerWidth(width = MeetTheme.sizes.sizeX6)
    }
}

@Composable
private fun SubscribersBlock(
    avatarUrl: List<Data>,
    onClickMorePeople: () -> Unit
) {
    Text(
        text = stringResource(CommonString.text_signed),
        color = Color.Black,
        style = MeetTheme.typography.interSemiBold24
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX16)
    PersonRow(
        avatarList = avatarUrl.map { it.image },
        onClickMorePeople = onClickMorePeople
    )
}

@Composable
private fun DescriptionBlock(
    description: String
) {
    Text(
        text = description,
        color = Color.Black,
        style = MeetTheme.typography.interMedium14
    )
}

@Composable
private fun ActionBlock(
    buttonState: FilledButtonState,
    buttonText: String
) {
    FilledButton(
        state = buttonState,
        buttonText = buttonText
    ) {
        //TODO
    }
    SpacerHeight(height = MeetTheme.sizes.sizeX8)
    //TODO: добавить условие
    Text(
        text = stringResource(CommonString.text_desc_under_subscribe_bt),
        color = MeetTheme.colors.primary,
        style = MeetTheme.typography.interMedium14
    )
    //TODO: добавить условие
}

@Composable
private fun CommonInfo(
    placeholder: Int,
    avatarUrl: String?,
    name: String,
    categories: List<Category>
) {
    PersonImage(
        placeholderImage = placeholder,
        avatarUrl = avatarUrl,
        size = 167.dp
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX8)
    Text(
        text = name,
        color = Color.Black,
        style = MeetTheme.typography.interBold34
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX2)
    FlexRow(
        horizontalGap = MeetTheme.sizes.sizeX6,
        verticalGap = MeetTheme.sizes.sizeX6,
        alignment = Alignment.Start
    ) {
        repeat(categories.size) { index ->
            Chip(
                text = categories[index].title,
                chipSize = ChipSize.MEDIUM,
                chipColors = ChipSelect.FALSE,
                chipClick = ChipClick.NOT_ON_CLICK
            ) {
                //TODO
            }
        }
    }
}
