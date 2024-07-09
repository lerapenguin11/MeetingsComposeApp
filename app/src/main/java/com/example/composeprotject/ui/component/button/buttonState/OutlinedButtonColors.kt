package com.example.composeprotject.ui.component.button.buttonState

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.theme.MeetTheme

class OutlinedButtonColors(
    private val params: ParamsOutlinedButtonColor
) : OutlinedButtonColorsState {
    override fun backgroundColor(state: ButtonState): Color {
        return when (state) {
            ButtonState.INITIAL, ButtonState.PRESSED, ButtonState.DISABLED -> params.backgroundColor
        }
    }

    override fun contentColor(state: ButtonState): Color {
        return when (state) {
            ButtonState.INITIAL -> params.contentColorActive
            ButtonState.PRESSED -> params.contentColorHover
            ButtonState.DISABLED -> params.contentColorDisabled
        }
    }

    override fun strokeColor(state: ButtonState): Color {
        return when (state) {
            ButtonState.INITIAL -> params.strokeColorActive
            ButtonState.PRESSED -> params.strokeColorHover
            ButtonState.DISABLED -> params.strokeColorDisabled
        }
    }
}

object OutlinedButtonDefaults {
    @Composable
    fun colors(
        backgroundColor: Color = Color.Transparent,
        contentColorActive: Color = MeetTheme.colors.brandDefault,
        contentColorHover: Color = MeetTheme.colors.brandDark,
        contentColorDisabled: Color = MeetTheme.colors.disabledButton,
        strokeColorActive: Color = MeetTheme.colors.brandDefault,
        strokeColorHover: Color = MeetTheme.colors.brandDark,
        strokeColorDisabled: Color = MeetTheme.colors.disabledButton
    ): OutlinedButtonColors {
        return OutlinedButtonColors(
            params = ParamsOutlinedButtonColor(
                backgroundColor = backgroundColor,
                contentColorActive = contentColorActive,
                contentColorDisabled = contentColorDisabled,
                contentColorHover = contentColorHover,
                strokeColorDisabled = strokeColorDisabled,
                strokeColorActive = strokeColorActive,
                strokeColorHover = strokeColorHover,
            )
        )
    }
}

private interface OutlinedButtonColorsState {
    fun backgroundColor(state: ButtonState): Color
    fun contentColor(state: ButtonState): Color
    fun strokeColor(state: ButtonState): Color
}