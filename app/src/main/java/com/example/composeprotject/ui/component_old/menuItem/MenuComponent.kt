package com.example.composeprotject.ui.component_old.menuItem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.composeprotject.R
import com.example.composeprotject.ui.component_old.avatar.ProfileAvatarContainer
import com.example.composeprotject.ui.component_old.text.BaseText
import com.example.composeprotject.ui.component_old.variant.avatar.AvatarState
import com.example.composeprotject.ui.component_old.variant.avatar.ProfileAvatarVariant
import com.example.composeprotject.ui.theme.MeetTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileMenuItem(
    modifier: Modifier = Modifier,
    name: String,
    numberPhone: String,
    avatarUrl: String?,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = MeetTheme.sizes.sizeX16, vertical = MeetTheme.sizes.sizeX8)
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileAvatarContainer(
                variant = ProfileAvatarVariant.TINY,
                colorContainer = MeetTheme.colors.neutralLine,
                avatarUrl = avatarUrl,
                contentDescription = R.string.text_content_description,
                state = AvatarState.DISPLAY
            )
            Spacer(modifier = modifier.width(MeetTheme.sizes.sizeX20))
            Column {
                BaseText(
                    text = name,
                    textColor = MeetTheme.colors.neutralActive,
                    textStyle = MeetTheme.typography.bodyText1
                )
                Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX2))
                BaseText(
                    text = numberPhone,
                    textColor = MeetTheme.colors.neutralDisabled,
                    textStyle = MeetTheme.typography.metadata1
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.ic_still_nav_menu),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun MyEventMenuItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    menuIcon : Int,
    menuName : Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = MeetTheme.sizes.sizeX16, vertical = MeetTheme.sizes.sizeX16)
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = menuIcon),
                contentDescription = stringResource(id = R.string.text_my_event)
            )
            Spacer(modifier = modifier.width(MeetTheme.sizes.sizeX6))
            Column {
                BaseText(
                    text = stringResource(id = menuName),
                    textColor = MeetTheme.colors.neutralActive,
                    textStyle = MeetTheme.typography.bodyText1
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.ic_still_nav_menu),
                    contentDescription = null
                )
            }
        }
    }
}