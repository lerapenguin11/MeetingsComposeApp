package com.example.composeprotject.ui.component.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.component.button.buttonState.FilledButtonColors
import com.example.composeprotject.ui.component.button.buttonState.FilledButtonDefaults
import com.example.composeprotject.ui.component.button.buttonState.OutlinedButtonColors
import com.example.composeprotject.ui.component.button.buttonState.OutlinedButtonDefaults
import com.example.composeprotject.ui.component.state.ProgressButtonState
import com.example.composeprotject.ui.component.button.buttonState.TextButtonColors
import com.example.composeprotject.ui.component.button.buttonState.TextButtonDefaults
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun ToggleMeetingButton(
    showMeeting: Boolean,
    onClick: () -> Unit,
    textFieldButton : Int,
    textOutlineButton : Int,
    state: ButtonState = ButtonState.INITIAL
){
    if (showMeeting) {
        FilledButton(
            onClick = onClick,
            buttonText = textFieldButton,
            state = state
        )
    } else {
        OutlinedButton(
            onClick = onClick,
            buttonText = textOutlineButton
        )
    }
}

@Composable
fun FilledButton(
    onClick: () -> Unit,
    state: ButtonState,
    colors: FilledButtonColors = FilledButtonDefaults.colors(),
    buttonText: Int,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    var changeableState by remember { mutableStateOf(ButtonState.INITIAL) }
    val isPressed = interactionSource.collectIsPressedAsState().value ?: false
    changeableState = state
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(height = 52.dp),
            onClick = { onClick() },
            interactionSource = interactionSource,
            enabled = changeableState != ButtonState.DISABLED,
            contentPadding = PaddingValues(vertical = MeetTheme.sizes.sizeX12),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = colors.backgroundColor(changeableState),
                containerColor = colors.backgroundColor(changeableState),
                contentColor = colors.contentColor(changeableState),
                disabledContentColor = colors.contentColor(changeableState)
            )
        ) {
            AnimatedVisibility(visible = isPressed) {
                changeableState = if (isPressed) {
                    ButtonState.PRESSED
                } else {
                    ButtonState.INITIAL
                }
            }
            BaseText(
                text = stringResource(id = buttonText),
                textStyle = MeetTheme.typography.subheading2,
                textColor = MeetTheme.colors.neutralWhite
            )
        }
    }
}

@Composable
fun FilledButtonWithProgressBar(
    modifier: Modifier = Modifier,
    state: ButtonState,
    colors: FilledButtonColors = FilledButtonDefaults.colors(),
    buttonText: Int,
    progressState : ProgressButtonState,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    var changeableState by remember { mutableStateOf(ButtonState.INITIAL) }
    val isPressed = interactionSource.collectIsPressedAsState().value ?: false
    changeableState = state
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(height = 52.dp),
            onClick = { onClick() },
            interactionSource = interactionSource,
            enabled = changeableState != ButtonState.DISABLED,
            contentPadding = PaddingValues(vertical = MeetTheme.sizes.sizeX12),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = colors.backgroundColor(changeableState),
                containerColor = colors.backgroundColor(changeableState),
                contentColor = colors.contentColor(changeableState),
                disabledContentColor = colors.contentColor(changeableState)
            )
        ) {
            AnimatedVisibility(visible = isPressed) {
                changeableState = if (isPressed) {
                    ButtonState.PRESSED
                } else {
                    ButtonState.INITIAL
                }
            }
            if (ProgressButtonState.LOADING == progressState){
                CircularProgressIndicator(
                    modifier = Modifier.width(28.dp),
                    color = MeetTheme.colors.neutralOffWhite,
                    trackColor = Color.Transparent,
                    strokeWidth = 2.dp
                )
            }else{
                BaseText(
                    text = stringResource(id = buttonText),
                    textStyle = MeetTheme.typography.subheading2,
                    textColor = MeetTheme.colors.neutralWhite
                )
            }
        }
    }
}

@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    state: ButtonState = ButtonState.INITIAL,
    colors: OutlinedButtonColors = OutlinedButtonDefaults.colors(),
    buttonText: Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    val changeableState = remember { mutableStateOf(state) }
    val isPressed = interactionSource.collectIsPressedAsState().value ?: false

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxSize(),
            onClick = { onClick() },
            interactionSource = interactionSource,
            enabled = changeableState.value != ButtonState.DISABLED,
            contentPadding = PaddingValues(vertical = MeetTheme.sizes.sizeX12),
            border = outlinedBorderStroke(
                width = 2,
                color = colors.strokeColor(changeableState.value)
            ),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = colors.backgroundColor(changeableState.value),
                disabledContentColor = colors.contentColor(changeableState.value),
                containerColor = colors.backgroundColor(changeableState.value),
                contentColor = colors.contentColor(changeableState.value)
            )
        ) {
            AnimatedVisibility(visible = isPressed) {
                if (isPressed) {
                    changeableState.value = ButtonState.PRESSED
                } else {
                    changeableState.value = ButtonState.INITIAL
                }
            }
            Text(
                stringResource(id = buttonText),
                style = MeetTheme.typography.subheading2
            )
        }
    }
}

@Composable
fun ImageOutlinedButton(
    icon: Int,
    onClick: () -> Unit,
    state: ButtonState = ButtonState.INITIAL,
    colors: OutlinedButtonColors = OutlinedButtonDefaults.colors(),
    modifier: Modifier = Modifier,
    contentDescription: Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    val changeableState = remember { mutableStateOf(state) }
    val isPressed = interactionSource.collectIsPressedAsState().value ?: false

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        OutlinedButton(
            modifier = modifier
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
            onClick = { onClick() },
            interactionSource = interactionSource,
            enabled = changeableState.value != ButtonState.DISABLED,
            contentPadding = PaddingValues(horizontal = (25.85).dp, vertical = 10.dp),
            border = outlinedBorderStroke(
                width = 2,
                color = colors.strokeColor(changeableState.value)
            ),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = colors.backgroundColor(changeableState.value),
                disabledContentColor = colors.contentColor(changeableState.value),
                containerColor = colors.backgroundColor(changeableState.value),
                contentColor = colors.contentColor(changeableState.value)
            )
        ) {
            AnimatedVisibility(visible = isPressed) {
                if (isPressed) {
                    changeableState.value = ButtonState.PRESSED
                } else {
                    changeableState.value = ButtonState.INITIAL
                }
            }
            Image(
                painter = painterResource(id = icon),
                contentDescription = stringResource(id = contentDescription)
            )
        }
    }
}

@Composable
fun TextButton(
    onClick: () -> Unit,
    state: ButtonState = ButtonState.INITIAL,
    colors: TextButtonColors = TextButtonDefaults.colors(),
    buttonText : Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    val changeableState = remember { mutableStateOf(state) }
    val isPressed = interactionSource.collectIsPressedAsState().value ?: false

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        TextButton(
            contentPadding = PaddingValues(vertical = MeetTheme.sizes.sizeX12),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.backgroundColor(changeableState.value),
                contentColor = colors.contentColor(changeableState.value),
                disabledContainerColor = colors.backgroundColor(changeableState.value),
                disabledContentColor = colors.contentColor(changeableState.value)
            ),
            onClick = { onClick() },
            interactionSource = interactionSource,
            enabled = changeableState.value != ButtonState.DISABLED
        ) {
            AnimatedVisibility(visible = isPressed) {
                if (isPressed) {
                    changeableState.value = ButtonState.PRESSED
                } else {
                    changeableState.value = ButtonState.INITIAL
                }
            }
            Text(
                stringResource(
                    id = buttonText
                ),
                color = colors.contentColor(changeableState.value),
                style = MeetTheme.typography.subheading2
            )
        }
    }
}

@Composable
fun outlinedBorderStroke(width: Int, color: Color) = BorderStroke(
    width = width.dp, color = color
)

private object NoRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() = Color.Red

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        0.0f,
        0.0f,
        0.0f,
        0.0f
    )
}
