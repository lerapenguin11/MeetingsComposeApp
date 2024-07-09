package com.example.composeprotject.ui.component.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun StandardDivider() {
    HorizontalDivider(
        color = MeetTheme.colors.neutralLine,
        thickness = MeetTheme.sizes.sizeX1
    )
}