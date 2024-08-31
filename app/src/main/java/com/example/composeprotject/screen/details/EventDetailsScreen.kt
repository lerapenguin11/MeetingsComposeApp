package com.example.composeprotject.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.composeprotject.model.details.MeetingStatus
import com.example.composeprotject.model.interest.Category
import com.example.composeprotject.ui.component.button.BottomActionBar
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
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
import com.example.composeprotject.ui.component.utils.eventDetailsDate
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.utils.lineBreakInAddress

@Composable
fun EventDetailsScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val status = MeetingStatus.INACTIVE

    Column(verticalArrangement = Arrangement.Bottom) {
        Column(
            modifier = modifier
                .padding(contentPadding)
                .padding(horizontal = MeetTheme.sizes.sizeX16)
                .verticalScroll(
                    rememberScrollState()
                )
                .weight(5f)
        ) {
            SpacerHeight(height = MeetTheme.sizes.sizeX8)

            val tags =
                listOf(Category(0, "Продажи"), Category(1, "Бизнес")) //TODO: удалить
            CommonInfo(
                avatarUrl = "https://avatars.mds.yandex.net/i?id=4ebda8904a6a5267c88ac41a32bd5a7451db3218-4827934-images-thumbs&n=13",
                title = "Как повышать грейд. Лекция Павла Хорикова",
                startDate = 1722489166,
                shortMeetingAddress = "Кожевенная линия, 40",
                categories = tags,
                description = LoremIpsum(words = 30).values.first(),
                status = status
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX32)
            LeaderInfo(
                name = "Павел Хориков",
                bio = "Ведущий специалист по подбору персонала в одной из крупнейших IT-компаний в ЕС.",
                avatarUrl = null,
                placeholder = CommonDrawables.ic_community_placeholder
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX32)
            LocationInfo(
                short = "Кожевенная линия, 40",
                full = "Севкабель Порт, Кожевенная линия, 40",
                metro = "Приморская",

                )
            SpacerHeight(height = MeetTheme.sizes.sizeX32)
            PeopleAtMeetings(
                meetingStatus = status,
                avatarList = listOf(
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                    "https://get.pxhere.com/photo/person-people-portrait-facial-expression-hairstyle-smile-emotion-portrait-photography-134689.jpg",
                )
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX32)
            OrganizerInfo(
                name = "The IT-Crowd",
                bio = "Сообщество профессионалов в сфере IT. Объединяем специалистов разных направлений для обмена опытом, знаниями и идеями.",
                placeholder = CommonDrawables.ic_community_placeholder,
                avatarUrl = null
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX32)
            OtherCommunityMeetings()
        }
        if (status == MeetingStatus.ACTIVE) {
            BottomActionBar(
                buttonText = "Записаться на встречу",
                descText = "Всего 30 мест. Если передумаете — отпишитесь",
                state = FilledButtonState.ACTIVE_PRIMARY
            ) {
                //TODO
            }
        }
    }
}

@Composable
private fun OtherCommunityMeetings(

) {
    Text(
        text = stringResource(CommonString.text_other_community_Meetings),
        color = Color.Black,
        style = MeetTheme.typography.interSemiBold24
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX16)
}

@Composable
private fun OrganizerInfo(
    placeholder: Int,
    name: String,
    bio: String,
    avatarUrl: String?
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

@Composable
private fun PeopleAtMeetings(
    meetingStatus: MeetingStatus,
    avatarList: List<String>
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
    }
    SpacerHeight(height = MeetTheme.sizes.sizeX16)
    PersonRow(
        avatarList = avatarList
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
                chipColors = ChipClick.FALSE
            ) {
                //TODO
            }
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
