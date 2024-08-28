package com.example.composeprotject.ui.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.button.buttonStyle.FilledButtonColorsDefault
import com.example.composeprotject.ui.component.button.buttonStyle.FilledButtonStateStyle
import com.example.composeprotject.ui.component.button.buttonStyle.SubscribeButtonStateStyle
import com.example.composeprotject.ui.component.button.buttonStyle.SubscribeButtonStyleDefault
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun BottomActionBar(
    buttonText: String,
    descText: String,
    state: FilledButtonState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = MeetTheme.sizes.sizeX10
        ),
        shape = RoundedCornerShape(
            topStart = MeetTheme.sizes.sizeX24,
            topEnd = MeetTheme.sizes.sizeX24,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        )
    ) {
        Column(
            modifier = modifier
                .padding(
                    top = MeetTheme.sizes.sizeX10,
                    end = MeetTheme.sizes.sizeX16,
                    start = MeetTheme.sizes.sizeX16,
                    bottom = MeetTheme.sizes.sizeX24
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = descText,
                style = MeetTheme.typography.interMedium14,
                color = MeetTheme.colors.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
            FilledButton(state = state, buttonText = buttonText) {
                onClick()
            }
        }
    }
}

@Composable
fun FilledButton(
    state: FilledButtonState,
    buttonText: String,
    modifier: Modifier = Modifier,
    colors: FilledButtonStateStyle = FilledButtonColorsDefault.colors(),
    onClick: () -> Unit
) {
    val changeableState by remember { mutableStateOf(state) }

    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { onClick() },
        enabled = !(changeableState == FilledButtonState.DISABLED || changeableState == FilledButtonState.LOADING),
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
            if (FilledButtonState.LOADING == state) {
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

@Composable
fun SubscribeButton(
    state: SubscribeButtonState,
    modifier: Modifier = Modifier,
    style: SubscribeButtonStateStyle = SubscribeButtonStyleDefault.style(),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(37.dp),
        onClick = { onClick() },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(MeetTheme.sizes.sizeX12)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = style.background(state = state),
                )
                .padding(
                    vertical = MeetTheme.sizes.sizeX8,
                    horizontal = (8.5).dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(21.dp),
                painter = painterResource(id = style.content(state = state)),
                contentDescription = null
            )
        }
    }
}