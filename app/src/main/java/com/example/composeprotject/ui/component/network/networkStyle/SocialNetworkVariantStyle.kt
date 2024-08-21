package com.example.composeprotject.ui.component.network.networkStyle

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.network.variant.SocialNetworkVariant

class SocialNetworkVariantStyle(private val params: ParamsSocialNetworkStyle) : SocialNetworkStyle {
    override fun contentStartPadding(variant: SocialNetworkVariant): Dp {
        return when (variant) {
            SocialNetworkVariant.HABR -> params.startPaddingHabr
            SocialNetworkVariant.TELEGRAM -> params.startPaddingTelegram
        }
    }

    override fun contentTopPadding(variant: SocialNetworkVariant): Dp {
        return when (variant) {
            SocialNetworkVariant.HABR -> params.topPaddingHabr
            SocialNetworkVariant.TELEGRAM -> params.topPaddingTelegram
        }
    }

    override fun contentEndPadding(variant: SocialNetworkVariant): Dp {
        return when (variant) {
            SocialNetworkVariant.HABR -> params.endPaddingHabr
            SocialNetworkVariant.TELEGRAM -> params.endPaddingTelegram
        }
    }

    override fun contentBottomPadding(variant: SocialNetworkVariant): Dp {
        return when (variant) {
            SocialNetworkVariant.HABR -> params.bottomPaddingHabr
            SocialNetworkVariant.TELEGRAM -> params.bottomPaddingTelegram
        }
    }

    override fun widthImage(variant: SocialNetworkVariant): Dp {
        return when (variant) {
            SocialNetworkVariant.HABR -> params.widthImageHabr
            SocialNetworkVariant.TELEGRAM -> params.widthImageTelegram
        }
    }

    override fun heightImage(variant: SocialNetworkVariant): Dp {
        return when (variant) {
            SocialNetworkVariant.HABR -> params.heightImageHabr
            SocialNetworkVariant.TELEGRAM -> params.heightImageTelegram
        }
    }

    override fun imageVariant(variant: SocialNetworkVariant): Int {
        return when (variant) {
            SocialNetworkVariant.HABR -> params.habrImage
            SocialNetworkVariant.TELEGRAM -> params.telegramImage
        }
    }
}

object SocialNetworkStyleDefault {
    fun style(
        startPaddingHabr: Dp = 13.dp,
        endPaddingHabr: Dp = 9.dp,
        topPaddingHabr: Dp = 11.dp,
        bottomPaddingHabr: Dp = 9.dp,
        startPaddingTelegram: Dp = 12.dp,
        endPaddingTelegram: Dp = 13.dp,
        bottomPaddingTelegram: Dp = 15.dp,
        topPaddingTelegram: Dp = 16.dp,
        widthImageHabr: Dp = 30.dp,
        widthImageTelegram: Dp = 27.dp,
        heightImageHabr: Dp = 32.dp,
        heightImageTelegram: Dp = 21.dp,
        habrImage: Int = R.drawable.ic_network_habr,
        telegramImage: Int = R.drawable.ic_network_tg
    ): SocialNetworkVariantStyle {
        return SocialNetworkVariantStyle(
            params = ParamsSocialNetworkStyle(
                startPaddingHabr = startPaddingHabr,
                endPaddingHabr = endPaddingHabr,
                topPaddingHabr = topPaddingHabr,
                bottomPaddingHabr = bottomPaddingHabr,
                startPaddingTelegram = startPaddingTelegram,
                endPaddingTelegram = endPaddingTelegram,
                bottomPaddingTelegram = bottomPaddingTelegram,
                topPaddingTelegram = topPaddingTelegram,
                widthImageHabr = widthImageHabr,
                widthImageTelegram = widthImageTelegram,
                heightImageHabr = heightImageHabr,
                heightImageTelegram = heightImageTelegram,
                habrImage = habrImage,
                telegramImage = telegramImage
            )
        )
    }
}

private interface SocialNetworkStyle {
    fun contentStartPadding(variant: SocialNetworkVariant): Dp
    fun contentTopPadding(variant: SocialNetworkVariant): Dp
    fun contentEndPadding(variant: SocialNetworkVariant): Dp
    fun contentBottomPadding(variant: SocialNetworkVariant): Dp
    fun widthImage(variant: SocialNetworkVariant): Dp
    fun heightImage(variant: SocialNetworkVariant): Dp
    fun imageVariant(variant: SocialNetworkVariant): Int
}


