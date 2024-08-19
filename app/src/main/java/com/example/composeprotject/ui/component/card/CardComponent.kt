package com.example.composeprotject.ui.component.card

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.button.SubscribeButton
import com.example.composeprotject.ui.component.card.cardStyle.EventCardStyle
import com.example.composeprotject.ui.component.card.cardStyle.EventCardStyleDefault
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Tag
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.image.CommunityImage
import com.example.composeprotject.ui.component.image.EventImage
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.utils.FlexRow
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun EventCard(
    eventId: String,
    avatarUrl: String?,
    eventName: String,
    dateEvent: String,
    locationEvent: String,
    tags: List<String>,
    variant: EventCardVariant,
    modifier: Modifier = Modifier,
    style: EventCardStyle = EventCardStyleDefault.eventCardStyle(),
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        modifier = modifier
            .width(width = style.widthImage(variant = variant))
    ) {
        EventImage(
            width = style.widthImage(variant = variant),
            height = style.heightImage(variant = variant),
            avatarUrl = avatarUrl,
            placeholderImage = R.drawable.ic_event_placeholder
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        BaseText(
            text = eventName,
            textStyle = style.titleTextStyle(variant = variant),
            textColor = MeetTheme.colors.black,
            maxLines = EVENT_NAME_MAX_LINE
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX2))
        BaseText(
            text = "$dateEvent · $locationEvent",
            textColor = MeetTheme.colors.darkGray,
            textStyle = MeetTheme.typography.interMedium14,
            maxLines = DATE_LOCATION_EVENT_MAX_LINE
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        FlexRow(
            maxRow = MAX_ROW,
            horizontalGap = 6.dp,
            verticalGap = 6.dp,
            alignment = Alignment.Start,
        ) {
            repeat(tags.size) { index ->
                Tag(
                    text = tags[index],
                    chipSize = ChipSize.SMALL,
                    chipColors = ChipClick.FALSE
                )
            }
        }
    }
}

@Composable
fun CommunityCard(
    communityName: String,
    avatarUrl: String?,
    buttonState: SubscribeButtonState,
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
            placeholderImage = R.drawable.ic_community_placeholder,
            avatarUrl = avatarUrl
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
        BaseText(
            text = communityName,
            maxLines = COMMUNITY_NAME_MAX_LINE,
            textColor = MeetTheme.colors.black,
            textStyle = MeetTheme.typography.interSemiBold14
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
        SubscribeButton(
            state = buttonState,
        ) {
            //TODO
        }
    }
}

private const val DATE_LOCATION_EVENT_MAX_LINE = 2
private const val EVENT_NAME_MAX_LINE = 2
private const val MAX_ROW = 2
private const val COMMUNITY_NAME_MAX_LINE = 1

@Composable
@Preview(showBackground = true)
fun CardTest() {
    CommunityCard(
        avatarUrl = null, communityName = "Супер тестировщики",
        buttonState = SubscribeButtonState.NOT_SUBSCRIBED_COMMUNITY
    ) {

    }
}