package com.example.composeprotject.ui.component.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.avatar.Avatar
import com.example.composeprotject.ui.component.avatar.variant.AvatarVariant
import com.example.composeprotject.ui.component.chip.Tag
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun Person(
    namePerson: String,
    avatarUrl: String?,
    tags: List<String>
) {
    Column(
        modifier = Modifier
            .width(104.dp)
    ) {
        Avatar(
            variant = AvatarVariant.BIG,
            contentDescription = R.string.text_avatars_people,
            placeholderImage = R.drawable.ic_avatar_placeholder,
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
        Tag(text = tags[0], chipColors = ChipClick.FALSE)
    }
}

private const val NAME_PERSON_MAX_LINE = 1