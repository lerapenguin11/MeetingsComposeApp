package com.example.composeprotject.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.utils.CommonDrawables

@Composable
fun SplashScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = with(modifier) {
            fillMaxSize()
                .paint(
                    painterResource(id = CommonDrawables.ic_splash_fon),
                    contentScale = ContentScale.FillBounds
                )
                .padding(contentPadding)
                .padding(horizontal = 27.dp)
        },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = CommonDrawables.ic_logo),
            contentDescription = null
        )
    }
}