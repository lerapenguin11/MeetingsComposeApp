package com.example.composeprotject.ui.component.switcher

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun CustomSwitch(isSwitchOn: Boolean, onSwitch: (Boolean) -> Unit) {
    val bgColor: Color by animateColorAsState(
        if (isSwitchOn) MeetTheme.colors.primary else MeetTheme.colors.inactiveSwitch,
        animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = LinearEasing),
        label = stringResource(CommonString.animated_switch_color)
    )
    val thumbColor: Color by animateColorAsState(
        targetValue = MeetTheme.colors.neutralWhite,
        animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = LinearEasing),
        label = stringResource(CommonString.animated_switch_color)
    )
    val offset by animateDpAsState(
        targetValue = if (isSwitchOn) {
            26.dp
        } else {
            1.71.dp
        }, animationSpec = tween(
            ANIMATION_DURATION_MILLIS, easing = LinearEasing
        ), label = stringResource(CommonString.offset)
    )
    Box(
        modifier = Modifier
            .width(48.dp)
            .height(24.dp)
            .clip(shape = CircleShape)
            .background(bgColor)
            .clickable {
                onSwitch(!isSwitchOn)
            },
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .offset(x = offset)
                .shadow(elevation = 8.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .background(thumbColor)
        )
    }
}

private const val ANIMATION_DURATION_MILLIS = 300