package com.example.composeprotject.ui.component.button

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.composeprotject.R
import com.example.composeprotject.ui.theme.MeetTheme

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun TopAppBarImageButton(
    content: Int,
    isAnim: Boolean = false,
    onClick: () -> Unit
) {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.ic_anim_progress)
    var atEnd by remember { mutableStateOf(false) }

    LaunchedEffect(isAnim) {
        if (isAnim) {
            atEnd = true
        }
    }

    IconButton(
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MeetTheme.colors.primary
        ),
        content = {
            Icon(
                painter = if (isAnim) rememberAnimatedVectorPainter(
                    image,
                    atEnd
                ) else painterResource(id = content),
                contentDescription = null
            )
        }
    )
}