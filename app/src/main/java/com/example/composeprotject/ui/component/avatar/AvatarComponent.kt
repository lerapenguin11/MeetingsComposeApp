package com.example.composeprotject.ui.component.avatar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.avatar.avatarStyle.AvatarSizeDefault
import com.example.composeprotject.ui.component.avatar.avatarStyle.AvatarSizeVariant
import com.example.composeprotject.ui.component.avatar.variant.AvatarVariant

@Composable
fun Avatar(
    variant: AvatarVariant,
    contentDescription: Int,
    placeholderImage: Int,
    avatarUrl: String?,
    modifier: Modifier = Modifier,
    avatarSize: AvatarSizeVariant = AvatarSizeDefault.avatarSize()
) {
    AsyncImage(
        modifier = modifier
            .size(avatarSize.size(variant = variant))
            .clip(CircleShape)
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
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AvatarTest() {
    Avatar(
        contentDescription = R.string.text_avatars_people,
        placeholderImage = R.drawable.ic_avatar_placeholder,
        avatarUrl = null,
        variant = AvatarVariant.BIG
    )
}