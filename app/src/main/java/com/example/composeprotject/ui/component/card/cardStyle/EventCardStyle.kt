package com.example.composeprotject.ui.component.card.cardStyle

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.card.variant.EventCardVariant
import com.example.composeprotject.ui.theme.MeetTheme

class EventCardStyle(private val params: ParamsEventCardStyle) : EventCardStyleVariant {
    override fun widthImage(variant: EventCardVariant): Dp {
        return when (variant) {
            EventCardVariant.BIG -> params.widthBigImage
            EventCardVariant.SMALL -> params.widthSmallImage
        }
    }

    override fun heightImage(variant: EventCardVariant): Dp {
        return when (variant) {
            EventCardVariant.BIG -> params.heightBigImage
            EventCardVariant.SMALL -> params.heightSmallImage
        }
    }

    override fun titleTextStyle(variant: EventCardVariant): TextStyle {
        return when (variant) {
            EventCardVariant.BIG -> params.bigTitle
            EventCardVariant.SMALL -> params.smallTitle
        }
    }
}

object EventCardStyleDefault {
    @Composable
    fun eventCardStyle(
        widthBigImage: Dp = 320.dp,
        widthSmallImage: Dp = 220.dp,
        heightBigImage: Dp = 180.dp,
        heightSmallImage: Dp = 147.dp,
        bigTitle: TextStyle = MeetTheme.typography.interBold34,
        smallTitle: TextStyle = MeetTheme.typography.interSemiBold18,
    ) = EventCardStyle(
        params = ParamsEventCardStyle(
            widthBigImage = widthBigImage,
            widthSmallImage = widthSmallImage,
            heightBigImage = heightBigImage,
            heightSmallImage = heightSmallImage,
            bigTitle = bigTitle,
            smallTitle = smallTitle
        )
    )
}

private interface EventCardStyleVariant {
    fun widthImage(variant: EventCardVariant): Dp
    fun heightImage(variant: EventCardVariant): Dp
    fun titleTextStyle(variant: EventCardVariant): TextStyle
}