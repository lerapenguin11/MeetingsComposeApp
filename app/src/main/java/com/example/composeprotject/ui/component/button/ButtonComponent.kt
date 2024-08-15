package com.example.composeprotject.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.button.buttonStyle.ButtonColorsDefault
import com.example.composeprotject.ui.component.button.buttonStyle.ButtonStyle
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun FilledButton(
    state: ButtonState,
    buttonText: String,
    modifier: Modifier = Modifier,
    colors: ButtonStyle = ButtonColorsDefault.colors(),
    onClick: () -> Unit
) {
    val changeableState by remember { mutableStateOf(state) }

    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { onClick() },
        enabled = !(changeableState == ButtonState.DISABLED || changeableState == ButtonState.LOADING),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(MeetTheme.sizes.sizeX16)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = colors.backgroundColor(state = state)
                )
                .padding(
                    top = MeetTheme.sizes.sizeX16,
                    bottom = MeetTheme.sizes.sizeX18,
                    start = MeetTheme.sizes.sizeX32,
                    end = MeetTheme.sizes.sizeX32
                ),
            contentAlignment = Alignment.Center
        ) {
            if (ButtonState.LOADING == state) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    color = MeetTheme.colors.neutralOffWhite,
                    trackColor = Color.Transparent,
                    strokeWidth = 2.dp
                )
            } else {
                BaseText(
                    text = buttonText,
                    textColor = colors.contentColor(state = state),
                    textStyle = MeetTheme.typography.interSemiBold18
                )
            }
        }
    }
}