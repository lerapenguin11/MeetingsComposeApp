package com.example.composeprotject.ui.component.avatar.avatarStyle

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.avatar.variant.AvatarVariant

class AvatarSizeVariant(private val params: ParamsAvatarSize) : AvatarSize {
    override fun size(variant: AvatarVariant): Dp {
        return when (variant) {
            AvatarVariant.BIG -> params.bigSize
            AvatarVariant.SMALL -> params.smallSize
        }
    }
}

object AvatarSizeDefault {
    fun avatarSize(
        bigSize: Dp = 104.dp,
        smallSize: Dp = 47.dp
    ): AvatarSizeVariant {
        return AvatarSizeVariant(
            params = ParamsAvatarSize(
                bigSize = bigSize,
                smallSize = smallSize
            )
        )
    }
}

private interface AvatarSize {
    fun size(variant: AvatarVariant): Dp
}