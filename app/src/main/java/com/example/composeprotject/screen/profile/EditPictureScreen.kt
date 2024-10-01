package com.example.composeprotject.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.imageCash
import com.example.composeprotject.ui.theme.MeetTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfileScreenPreview() {
    EditPictureScreen(
        null, null
    ) {}
}

private val TriangleShape = GenericShape { size, _ ->
    // 1)
    moveTo(size.width / 2f, 0f)

    // 2)
    lineTo(size.width, size.height)

    // 3)
    lineTo(0f, size.height)

    close()
}


@Composable
fun EditPictureScreen(
    currentPhoto: String?,
    galleryUri: String?,
    modifier: Modifier = Modifier,
    mod: Modifier = Modifier,
    onCancel: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(size = MeetTheme.sizes.sizeX10))
                .padding(horizontal = MeetTheme.sizes.sizeX16, vertical = MeetTheme.sizes.sizeX20)
                .clickable {
                    onCancel()
                }
        ) {
            Image(
                modifier = Modifier
                    .size(size = MeetTheme.sizes.sizeX24),
                painter = painterResource(id = CommonDrawables.ic_close_bt),
                contentDescription = null
            )
        }
        Box(
            modifier = with(modifier) {
                fillMaxWidth()
                    .weight(3.5f)
            }
        ) {
            AsyncImage(
                model = imageCash(
                    context = LocalContext.current,
                    imageUrl = galleryUri ?: currentPhoto
                ),
                placeholder = painterResource(id = CommonDrawables.ic_avatar_user_profile),
                error = painterResource(id = CommonDrawables.ic_avatar_user_profile),
                contentDescription = stringResource(CommonString.text_avatar),
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(height = 375.dp)
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithContent {
                    drawContent()
                    drawCircle(
                        color = Color(0xFFFFFFFF),
                        center = Offset(x = size.width / 2, y = size.height / 2),
                        radius = size.width / 2.15f,
                        blendMode = BlendMode.DstOut
                    )
                }
                .background(color = MeetTheme.colors.blackTransparent)
            )
        }
        Column(
            modifier = Modifier
                .weight(2.5f)
                .padding(horizontal = MeetTheme.sizes.sizeX16),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        //TODO
                    },
                text = stringResource(CommonString.text_choose_another_photo),
                color = MeetTheme.colors.darkGray,
                style = MeetTheme.typography.interMedium18
            )
            SpacerHeight(height = 24.dp)
            FilledButton(
                state = FilledButtonState.ACTIVE_PRIMARY,
                buttonText = stringResource(id = CommonString.text_save)
            ) {
                //TODO
            }
            SpacerHeight(height = 28.dp)
        }
    }
}