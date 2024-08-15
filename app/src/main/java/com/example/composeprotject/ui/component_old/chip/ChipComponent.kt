package com.example.composeprotject.ui.component_old.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component_old.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun ChipOld(text: String) {
    Box(
        modifier = Modifier.padding(
            top = MeetTheme.sizes.sizeX4,
            bottom = MeetTheme.sizes.sizeX4
        )
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(40.dp))
                .background(color = MeetTheme.colors.brandBG)
        ) {
            BaseText(
                text = text,
                textStyle = MeetTheme.typography.metadata3,
                textColor = MeetTheme.colors.brandDarkInnerShadow,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
    }
}