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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.model.interest.Category
import com.example.composeprotject.model.meeting.Meeting
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.card.EventCard
import com.example.composeprotject.ui.component.card.EventCardFillMaxWidth
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
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
import kotlin.random.Random
import kotlin.random.nextUInt

@Composable
fun CommunityDetailsScreen(
    communityId: Int,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onClickMorePeople: (Int) -> Unit,
    onClickEvent: (Meeting) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = MeetTheme.sizes.sizeX16)
            ) {
                val tags =
                    listOf(
                        Category(0, "Продажи"),
                        Category(1, "Тестирование"),
                        Category(1, "Карьера"),
                        Category(1, "Бизнес"),
                        Category(1, "Дизайн"),
                        Category(1, "Разработка")
                    ) //TODO: удалить
                val desc = """
            Сообщество профессионалов в сфере IT. 
            Объединяем специалистов разных направлений для обмена опытом, знаниями и идеями.
        """.trimIndent() //TODO: удалить

                SpacerHeight(height = MeetTheme.sizes.sizeX8)
                CommonInfo(
                    placeholder = CommonDrawables.ic_community_placeholder,
                    avatarUrl = null,
                    name = "The IT Crowd",
                    categories = tags
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX26)
                ActionBlock(
                    buttonText = stringResource(CommonString.text_subscribe),
                    buttonState = FilledButtonState.ACTIVE_PRIMARY
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX32)
                DescriptionBlock(
                    description = desc
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX32)
                SubscribersBlock(
                    avatarUrl = listOf(null, null, null, null, null, null, null, null, null),
                    onClickMorePeople = {
                        onClickMorePeople(0)
                    } //TODO передать id сообщества
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX32)
                Text(
                    text = stringResource(CommonString.text_meeting),
                    color = Color.Black,
                    style = MeetTheme.typography.interSemiBold24
                )
                SpacerHeight(height = MeetTheme.sizes.sizeX16)
            }
        }
        //TODO: Встречи
        items(items = events()) { event ->
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
                itemsIndexed(items = events()) { index, event ->
                    PastMeetingsBlock(
                        index = index,
                        event = event,
                        eventSize = events().size,
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
        variant = EventCardVariant.SMALL
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
    avatarUrl: List<String?>,
    onClickMorePeople: () -> Unit
) {
    Text(
        text = stringResource(CommonString.text_signed),
        color = Color.Black,
        style = MeetTheme.typography.interSemiBold24
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX16)
    PersonRow(
        avatarList = avatarUrl,
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
                chipColors = ChipClick.FALSE
            ) {
                //TODO
            }
        }
    }
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
