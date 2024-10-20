package com.example.composeprotject.ui.component.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composeprotject.screen.state.SubscriptionCapabilityStatus
import com.example.composeprotject.ui.component.button.SubscribeButton
import com.example.composeprotject.ui.component.card.cardStyle.EventCardStyle
import com.example.composeprotject.ui.component.card.cardStyle.EventCardStyleDefault
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.image.CommunityImage
import com.example.composeprotject.ui.component.image.EventImage
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.component.utils.eventDate
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.domain.model.community.Community
import com.example.domain.model.event.Meeting
import com.example.domain.model.userLists.UserCommunities
import com.example.domain.model.userLists.UserEvents

@Composable
fun EventCard(
    meeting: Meeting,
    variant: EventCardVariant,
    modifier: Modifier = Modifier,
    style: EventCardStyle = EventCardStyleDefault.eventCardStyle(),
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = 4.dp,
            bottomStart = 4.dp
        ),
        modifier = modifier
            .width(width = style.widthImage(variant = variant))
    ) {
        Text(text = "TEST")
        /*EventImage(
            width = style.widthImage(variant = variant),
            height = style.heightImage(variant = variant),
            avatarUrl = meeting.avatarUrl,
            placeholderImage = CommonDrawables.ic_event_placeholder
        )*/
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        BaseText(
            text = meeting.title,
            textStyle = style.titleTextStyle(variant = variant),
            textColor = MeetTheme.colors.black,
            maxLines = EVENT_NAME_MAX_LINE
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX2))
        BaseText(
            text = "${eventDate(timestamp = meeting.startDate)} · ${meeting.shortAddress}",
            textColor = MeetTheme.colors.darkGray,
            textStyle = MeetTheme.typography.interMedium14,
            maxLines = DATE_LOCATION_EVENT_MAX_LINE
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        FlexRow(
            maxRow = MAX_ROW,
            horizontalGap = 6.dp,
            verticalGap = 6.dp,
            alignment = Alignment.Start
        ) {
            repeat(meeting.categories.size) { index ->
                Chip(
                    text = meeting.categories[index].title,
                    chipSize = ChipSize.SMALL,
                    chipColors = ChipSelect.FALSE,
                    chipClick = ChipClick.NOT_ON_CLICK,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun UserEventCard(
    meeting: UserEvents,
    variant: EventCardVariant,
    modifier: Modifier = Modifier,
    style: EventCardStyle = EventCardStyleDefault.eventCardStyle(),
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = 4.dp,
            bottomStart = 4.dp
        ),
        modifier = modifier
            .width(width = style.widthImage(variant = variant))
    ) {
        EventImage(
            width = style.widthImage(variant = variant),
            height = style.heightImage(variant = variant),
            avatarUrl = meeting.avatarUrl,
            placeholderImage = CommonDrawables.ic_event_placeholder
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        BaseText(
            text = meeting.title,
            textStyle = style.titleTextStyle(variant = variant),
            textColor = MeetTheme.colors.black,
            maxLines = EVENT_NAME_MAX_LINE
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX2))
        BaseText(
            text = "${eventDate(timestamp = meeting.startDate)} · ${meeting.shortAddress}",
            textColor = MeetTheme.colors.darkGray,
            textStyle = MeetTheme.typography.interMedium14,
            maxLines = DATE_LOCATION_EVENT_MAX_LINE
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        FlexRow(
            maxRow = MAX_ROW,
            horizontalGap = 6.dp,
            verticalGap = 6.dp,
            alignment = Alignment.Start
        ) {
            repeat(meeting.categories.size) { index ->
                Chip(
                    text = meeting.categories[index].title,
                    chipSize = ChipSize.SMALL,
                    chipColors = ChipSelect.FALSE,
                    chipClick = ChipClick.NOT_ON_CLICK,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun EventCardFillMaxWidth(
    meeting: Meeting,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = 4.dp,
            bottomStart = 4.dp
        ),
    ) {
        Text(text = "TEST")
        /*EventDetailsImage(
            height = 180.dp,
            avatarUrl = meeting.avatarUrl,
            placeholderImage = CommonDrawables.ic_event_placeholder
        )*/
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        BaseText(
            text = meeting.title,
            textStyle = MeetTheme.typography.interSemiBold24,
            textColor = MeetTheme.colors.black,
            maxLines = EVENT_NAME_MAX_LINE
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX2))
        BaseText(
            text = "${eventDate(timestamp = meeting.startDate)} · ${meeting.shortAddress}",
            textColor = MeetTheme.colors.darkGray,
            textStyle = MeetTheme.typography.interMedium14,
            maxLines = DATE_LOCATION_EVENT_MAX_LINE
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX2))
        FlexRow(
            maxRow = MAX_ROW,
            horizontalGap = 6.dp,
            verticalGap = 6.dp,
            alignment = Alignment.Start
        ) {
            repeat(meeting.categories.size) { index ->
                Chip(
                    text = meeting.categories[index].title,
                    chipSize = ChipSize.SMALL,
                    chipColors = ChipSelect.FALSE,
                    chipClick = ChipClick.NOT_ON_CLICK,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun CommunityCard(
    community: Community,
    buttonState: SubscribeButtonState,
    state: SubscriptionCapabilityStatus,
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit
) {
    Card(
        modifier = modifier.width(104.dp),
        onClick = { onClickCard() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
    ) {
        Text(text = "TEST")
        /*CommunityImage(
            placeholderImage = CommonDrawables.ic_community_placeholder,
            avatarUrl = community.avatarUrl
        )*/
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
        BaseText(
            text = community.title,
            maxLines = COMMUNITY_NAME_MAX_LINE,
            textColor = MeetTheme.colors.black,
            textStyle = MeetTheme.typography.interSemiBold14
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
        if (state == SubscriptionCapabilityStatus.THERE_SUBSCRIPTION) {
            SubscribeButton(
                state = buttonState,
            ) {
                //TODO
            }
        }
    }
}

@Composable
fun UserCommunityCard(
    community: UserCommunities,
    buttonState: SubscribeButtonState,
    state: SubscriptionCapabilityStatus,
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit
) {
    Card(
        modifier = modifier.width(104.dp),
        onClick = { onClickCard() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
    ) {
        CommunityImage(
            placeholderImage = CommonDrawables.ic_community_placeholder,
            avatarUrl = community.avatarUrl
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
        BaseText(
            text = community.title,
            maxLines = COMMUNITY_NAME_MAX_LINE,
            textColor = MeetTheme.colors.black,
            textStyle = MeetTheme.typography.interSemiBold14
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
        if (state == SubscriptionCapabilityStatus.THERE_SUBSCRIPTION) {
            SubscribeButton(
                state = buttonState,
            ) {
                //TODO
            }
        }
    }
}

@Composable
fun EventViewAllCard(
    variant: EventCardVariant,
    modifier: Modifier = Modifier,
    style: EventCardStyle = EventCardStyleDefault.eventCardStyle(),
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MeetTheme.colors.secondary,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = MeetTheme.sizes.sizeX16,
            bottomStart = MeetTheme.sizes.sizeX16
        ),
        modifier = modifier
            .width(width = 156.dp)
            .height(height = style.heightImage(variant = variant))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(all = 10.dp),
                text = stringResource(CommonString.text_view_all),
                color = MeetTheme.colors.primary,
                style = MeetTheme.typography.interMedium18,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CommunityViewAllCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MeetTheme.colors.secondary,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = MeetTheme.sizes.sizeX16,
            bottomStart = MeetTheme.sizes.sizeX16
        ),
        modifier = modifier
            .width(width = 104.dp)
            .height(height = 104.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(CommonString.text_all),
                color = MeetTheme.colors.primary,
                style = MeetTheme.typography.interMedium18,
                textAlign = TextAlign.Center
            )
        }
    }
}

private const val DATE_LOCATION_EVENT_MAX_LINE = 2
private const val EVENT_NAME_MAX_LINE = 2
private const val MAX_ROW = 1
private const val COMMUNITY_NAME_MAX_LINE = 1