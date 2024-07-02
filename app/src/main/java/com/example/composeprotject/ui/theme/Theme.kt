package com.example.composeprotject.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MeetTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides LightColorScheme,
        LocalTypography provides MeetTypographyValue,
        LocalSizeSystem provides EventsLocalSizeSystem,
    ) {
        MaterialTheme(
            content = content
        )
    }
}

object MeetTheme {
    val colors: MeetColorScheme
        @Composable get() = LocalColorScheme.current
    val typography: MeetTypography
        @Composable get() = LocalTypography.current
    val sizes: MeetSizeSystem
        @Composable get() = LocalSizeSystem.current
}
