package com.example.composeprotject.ui.component_old.avatar


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composeprotject.R
import com.example.composeprotject.ui.component_old.text.BaseText
import com.example.composeprotject.ui.component_old.variant.avatar.AvatarProfileImage
import com.example.composeprotject.ui.component_old.variant.avatar.AvatarProfileImageDefault
import com.example.composeprotject.ui.component_old.variant.avatar.ProfileAvatarVariant
import com.example.composeprotject.ui.component_old.variant.avatar.AvatarState
import com.example.composeprotject.ui.theme.MeetTheme

private const val MAX_SHOW_AVATARS = 5

@Composable
fun ProfileAvatarContainer(
    variant: ProfileAvatarVariant = ProfileAvatarVariant.LARGE,
    colorContainer: Color,
    modifier: Modifier = Modifier,
    imageVariant: AvatarProfileImage = AvatarProfileImageDefault.image(),
    avatarUrl: String?,
    contentDescription: Int,
    state: AvatarState
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .size(imageVariant.sizeAvatarContainer(variant = variant).dp)
                .clip(CircleShape)
                .background(colorContainer)
        ) {
            AsyncImage(
                modifier = modifier
                    .clip(CircleShape)
                    .fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(avatarUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(imageVariant.placeholderImageSize(variant = variant)),
                error = painterResource(id = imageVariant.placeholderImageSize(variant = variant)),
                contentDescription = stringResource(id = contentDescription),
                contentScale = ContentScale.Fit
            )
        }
        if (state == AvatarState.EDITING) {
            Image(
                modifier = modifier
                    .padding(end = imageVariant.paddingEditingImage(variant = variant).dp)
                    .offset(y = imageVariant.offsetEditingImage(variant = variant).dp)
                    .size(imageVariant.sizeEditingImage(variant = variant).dp),
                painter = painterResource(
                    id = R.drawable.ic_add_photo_user
                ),
                contentDescription = stringResource(id = R.string.text_button_add_photo_user)
            )
        }
    }
}

@Composable
fun AvatarStatus(
    placeholderImage: Int,
    avatarUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: Int,
    borderColor: Color = MeetTheme.colors.purpleColor
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .padding(all = MeetTheme.sizes.sizeX4),
        contentAlignment = Alignment.TopEnd
    ) {
        AsyncImage(
            modifier = modifier
                .size(48.dp)
                .border(
                    BorderStroke(BORDER_WIDTH.dp, borderColor),
                    RoundedCornerShape(BORDER_CORNER_SHAPE.dp)
                )
                .padding(BORDER_WIDTH.dp)
                .clip(RoundedCornerShape(CLIP_CORNER_SHAPE.dp))
                .fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarUrl)
                .crossfade(true)
                .build(),
            error = painterResource(placeholderImage),
            placeholder = painterResource(placeholderImage),
            contentDescription = stringResource(id = contentDescription),
            contentScale = ContentScale.Crop
        )
        /*Box(
            modifier = modifier
                .offset(x = 0.dp, y = (-2).dp)
                .border(2.dp, MeetTheme.colors.neutralOffWhite, CircleShape)
                .clip(shape = CircleShape)
                .background(MeetTheme.colors.primaryDefault)
                .size(14.dp)
        )*/
    }
}

@Composable
fun AttendeesRow(
    avatarList: List<String>,
    maxShowAvatars: Int = MAX_SHOW_AVATARS,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((-24).dp)
    ) {
        if (avatarList.isEmpty()) {
            BaseText(
                text = stringResource(
                    id = R.string.text_attendees_row
                ),
                textStyle = MeetTheme.typography.bodyText1,
                textColor = MeetTheme.colors.neutralActive
            )
        } else {
            avatarList.take(maxShowAvatars).forEachIndexed { index, element ->
                val zIndex = avatarList.size - index

                AvatarStatus(
                    placeholderImage = R.drawable.ic_profile_blue_fon,
                    avatarUrl = element,
                    contentDescription = R.string.text_avatars_people,
                    modifier = Modifier.zIndex(zIndex.toFloat())
                )
            }
            if (avatarList.size > maxShowAvatars) {
                BaseText(
                    modifier = modifier.padding(start = 34.dp),
                    text = "+${avatarList.size - maxShowAvatars}",
                    textStyle = MeetTheme.typography.bodyText1,
                    textColor = MeetTheme.colors.neutralActive
                )
            }
        }
    }
}

@Composable
fun RoundedAvatarMeetings(
    placeholderImage: Int,
    avatarUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(MeetTheme.sizes.sizeX56)
            .padding(all = MeetTheme.sizes.sizeX4)
            .clip(RoundedCornerShape(MeetTheme.sizes.sizeX16))
            .background(color = Color.White)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(placeholderImage),
            error = painterResource(placeholderImage),
            contentDescription = stringResource(R.string.text_avatar_meetings),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

private const val BORDER_CORNER_SHAPE = 16
private const val CLIP_CORNER_SHAPE = 14
private const val BORDER_WIDTH = 2