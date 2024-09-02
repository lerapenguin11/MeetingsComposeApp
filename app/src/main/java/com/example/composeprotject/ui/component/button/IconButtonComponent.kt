package com.example.composeprotject.ui.component.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun TopAppBarImageButton(
    content: Int,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MeetTheme.colors.primary
        ),
        content = {
            Icon(painterResource(id = content), null)
        }
    )
}