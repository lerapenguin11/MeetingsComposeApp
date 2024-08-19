package com.example.composeprotject.ui.component.card

import androidx.compose.foundation.clickable
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
import com.example.composeprotject.ui.component.card.cardStyle.EventCardStyle
import com.example.composeprotject.ui.component.card.cardStyle.EventCardStyleDefault
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.component.chip.Tag
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.image.EventImage
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
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX16,
            topEnd = MeetTheme.sizes.sizeX16,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        modifier = modifier
            .clickable(onClick = onClick)
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

private const val DATE_LOCATION_EVENT_MAX_LINE = 2
private const val EVENT_NAME_MAX_LINE = 2
private const val MAX_ROW = 2

@Composable
@Preview(showBackground = true, backgroundColor = 348444L)
fun CardTest() {
    EventCard(
        variant = EventCardVariant.BIG,
        eventId = "dkfbdkjfd-dfdfdjf9dfdf-fdfd",
        eventName = "Python days kcnvkdnvdn kvdndk",
        locationEvent = "Кожевенная линия, 40",
        dateEvent = "10 августа",
        tags = listOf(
            "Web", "Mobile", "Бизнес",
            "Продажи", "Девопс", "Тестирование",
            "Web", "Mobile", "Бизнес",
            "Продажи", "Девопс", "Тестирование"
        ),
        avatarUrl = null
    ) {}
}