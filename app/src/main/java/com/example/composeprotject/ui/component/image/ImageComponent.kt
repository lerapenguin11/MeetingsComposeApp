package com.example.composeprotject.ui.component.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.imageCash
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun EventDetailsImage(
    height: Dp,
    avatarUrl: String?,
    placeholderImage: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = height)
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
fun EventImage(
    width: Dp,
    height: Dp,
    avatarUrl: String?,
    placeholderImage: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = width, height = height)
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
fun CommunityImage(
    placeholderImage: Int,
    avatarUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 104.dp, height = 104.dp)
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