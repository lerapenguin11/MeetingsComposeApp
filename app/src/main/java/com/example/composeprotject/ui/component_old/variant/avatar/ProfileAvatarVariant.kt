package com.example.composeprotject.ui.component_old.variant.avatar

import com.example.composeprotject.R

enum class AvatarState() {
    DISPLAY,
    EDITING
}

enum class ProfileAvatarVariant {
    LARGE,
    MEDIUM,
    TINY
}

data class SizeAvatarContainer(
    val large: Int,
    val medium: Int,
    val tiny: Int
)

class AvatarProfileImage(
    private val large: Int,
    private val medium: Int,
    private val tiny: Int,
    private val max: Int,
    private val min: Int,
    private val sizeAvatarContainer: SizeAvatarContainer
) : SizeImageAvatarProfile {
    override fun placeholderImageSize(variant: ProfileAvatarVariant): Int {
        return when (variant) {
            ProfileAvatarVariant.LARGE -> large
            ProfileAvatarVariant.MEDIUM -> medium
            ProfileAvatarVariant.TINY -> tiny
        }
    }

    override fun sizeEditingImage(variant: ProfileAvatarVariant): Int {
        return when (variant) {
            ProfileAvatarVariant.LARGE -> max
            ProfileAvatarVariant.MEDIUM -> min
            ProfileAvatarVariant.TINY -> 0
        }
    }

    override fun sizeAvatarContainer(variant: ProfileAvatarVariant): Int {
        return when (variant) {
            ProfileAvatarVariant.LARGE -> sizeAvatarContainer.large
            ProfileAvatarVariant.MEDIUM -> sizeAvatarContainer.medium
            ProfileAvatarVariant.TINY -> sizeAvatarContainer.tiny
        }
    }

    override fun paddingEditingImage(variant: ProfileAvatarVariant): Int {
        return when (variant) {
            ProfileAvatarVariant.LARGE -> 6
            ProfileAvatarVariant.MEDIUM -> 3
            ProfileAvatarVariant.TINY -> 0
        }
    }

    override fun offsetEditingImage(variant: ProfileAvatarVariant): Int {
        return when (variant) {
            ProfileAvatarVariant.LARGE -> 2
            ProfileAvatarVariant.MEDIUM -> 1
            ProfileAvatarVariant.TINY -> 0
        }
    }
}

object AvatarProfileImageDefault {
    fun image(
        largeAvatar: Int = R.drawable.ic_profile_112,
        mediumAvatar: Int = R.drawable.ic_profile_100,
        tinyAvatar: Int = R.drawable.ic_profile_50,
        max: Int = 48,
        min: Int = 24,
        largeContainer: Int = 200,
        mediumContainer: Int = 100,
        tinyContainer: Int = 50,

        ): AvatarProfileImage {
        return AvatarProfileImage(
            large = largeAvatar,
            medium = mediumAvatar,
            tiny = tinyAvatar,
            max = max,
            min = min,
            sizeAvatarContainer = SizeAvatarContainer(
                large = largeContainer,
                medium = mediumContainer,
                tiny = tinyContainer
            )
        )
    }
}

interface SizeImageAvatarProfile {
    fun placeholderImageSize(variant: ProfileAvatarVariant): Int
    fun sizeEditingImage(variant: ProfileAvatarVariant): Int
    fun sizeAvatarContainer(variant: ProfileAvatarVariant): Int
    fun paddingEditingImage(variant: ProfileAvatarVariant): Int
    fun offsetEditingImage(variant: ProfileAvatarVariant): Int
}