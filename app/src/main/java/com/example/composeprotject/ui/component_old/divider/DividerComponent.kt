package com.example.composeprotject.ui.component_old.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun StandardDivider() {
    HorizontalDivider(
        color = MeetTheme.colors.neutralLine,
        thickness = MeetTheme.sizes.sizeX1
    )
}