package com.example.composeprotject.ui.component.progressBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(104.dp)
            .clip(RoundedCornerShape(size = MeetTheme.sizes.sizeX16))
            .background(
                color = MeetTheme.colors.secondary,
                shape = RoundedCornerShape(size = MeetTheme.sizes.sizeX16)
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
            color = MeetTheme.colors.primary,
            trackColor = Color.Transparent,
            strokeWidth = 4.dp
        )
    }
}