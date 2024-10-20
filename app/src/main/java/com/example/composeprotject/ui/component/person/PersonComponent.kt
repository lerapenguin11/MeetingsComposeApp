package com.example.composeprotject.ui.component.person

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.composeprotject.ui.component.avatar.Avatar
import com.example.composeprotject.ui.component.avatar.variant.AvatarVariant
import com.example.composeprotject.ui.component.chip.Chip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.imageCash
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.domain.model.interest.Interest

@Composable
fun PersonImage(
    size: Dp,
    placeholderImage: Int,
    avatarUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size = size)
            .clip(RoundedCornerShape(MeetTheme.sizes.sizeX16))
            .background(color = Color.Transparent)
    ) {
        AsyncImage(
            model = imageCash(
                context = LocalContext.current,
                imageUrl = avatarUrl
            ),
            placeholder = painterResource(placeholderImage),
            error = painterResource(placeholderImage),
            contentDescription = stringResource(CommonString.text_avatar_meetings),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Person(
    namePerson: String,
    avatarUrl: String?,
    tags: List<Interest>
) {
    Column(
        modifier = Modifier
            .width(104.dp)
    ) {
        Avatar(
            variant = AvatarVariant.BIG,
            contentDescription = CommonString.text_avatars_people,
            placeholderImage = CommonDrawables.ic_avatar_placeholder,
            avatarUrl = avatarUrl
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
        BaseText(
            text = namePerson,
            maxLines = NAME_PERSON_MAX_LINE,
            textColor = MeetTheme.colors.black,
            textStyle = MeetTheme.typography.interMedium18
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX4))
        Chip(
            text = tags[0].title,
            chipColors = ChipSelect.FALSE,
            chipClick = ChipClick.NOT_ON_CLICK,
            chipSize = ChipSize.SMALL,
            onClick = {}
        )
    }
}

@Composable
fun PersonRow(
    avatarList: List<String?>,
    modifier: Modifier = Modifier,
    onClickMorePeople: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((AVATAR_OFFSET).dp)
    ) {
        if (avatarList.isEmpty()) {
            BaseText(
                text = stringResource(
                    id = CommonString.text_attendees_row
                ),
                textStyle = MeetTheme.typography.bodyText1,
                textColor = MeetTheme.colors.neutralActive
            )
        } else {
            avatarList.take(MAX_SHOW_AVATARS).forEachIndexed { index, element ->
                val zIndex = avatarList.size - index
                Avatar(
                    modifier = Modifier.zIndex(zIndex = (-zIndex).toFloat()),
                    variant = AvatarVariant.SMALL,
                    contentDescription = CommonString.text_avatars_people,
                    placeholderImage = CommonDrawables.ic_avatar_placeholder,
                    avatarUrl = element
                )
            }
            if (avatarList.size > MAX_SHOW_AVATARS) {
                MorePeople(
                    avatarsSize = avatarList.size,
                    onClickMorePeople = onClickMorePeople
                )
            }
        }
    }
}

@Composable
fun MorePeople(
    avatarsSize: Int,
    modifier: Modifier = Modifier,
    onClickMorePeople: () -> Unit
) {
    Box(
        modifier = modifier
            .size(size = 47.dp)
            .border(width = MeetTheme.sizes.sizeX2, color = Color.White, shape = CircleShape)
            .background(color = MeetTheme.colors.secondary, shape = CircleShape)
            .clip(shape = CircleShape)
            .clickable { onClickMorePeople() },
        contentAlignment = Alignment.Center
    ) {
        BaseText(
            text = "$PLUS ${avatarsSize - MAX_SHOW_AVATARS}",
            textStyle = MeetTheme.typography.interMedium14,
            textColor = MeetTheme.colors.primary
        )
    }
}

private const val NAME_PERSON_MAX_LINE = 1
private const val MAX_SHOW_AVATARS = 8
private const val PLUS = "+"
private const val AVATAR_OFFSET = -10