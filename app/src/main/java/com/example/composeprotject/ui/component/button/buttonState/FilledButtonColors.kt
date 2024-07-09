package com.example.composeprotject.ui.component.button.buttonState

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.theme.MeetTheme

class FilledButtonColors(
    private val params: ParamsFilledButtonColor
) : FilledButtonColorsState {
    override fun backgroundColor(state: ButtonState): Color {
        return when (state) {
            ButtonState.INITIAL -> params.backgroundColorActive
            ButtonState.PRESSED -> params.backgroundColorHover
            ButtonState.DISABLED -> params.backgroundColorDisabled
        }
    }

    override fun contentColor(state: ButtonState): Color {
        return when (state) {
            ButtonState.INITIAL -> params.contentColorActive
            ButtonState.PRESSED -> params.contentColorHover
            ButtonState.DISABLED -> params.contentColorDisabled
        }
    }
}

object FilledButtonDefaults {
    @Composable
    fun colors(
        backgroundColorActive: Color = MeetTheme.colors.brandDefault,
        backgroundColorHover: Color = MeetTheme.colors.brandDark,
        backgroundColorDisabled: Color = MeetTheme.colors.disabledButton,
        contentColorActive: Color = MeetTheme.colors.neutralWhite,
        contentColorHover: Color = MeetTheme.colors.neutralWhite,
        contentColorDisabled: Color = MeetTheme.colors.neutralWhite,
    ): FilledButtonColors {
        return FilledButtonColors(
            params = ParamsFilledButtonColor(
                backgroundColorActive = backgroundColorActive,
                backgroundColorHover = backgroundColorHover,
                backgroundColorDisabled = backgroundColorDisabled,
                contentColorActive = contentColorActive,
                contentColorDisabled = contentColorDisabled,
                contentColorHover = contentColorHover
            )
        )
    }
}

private interface FilledButtonColorsState {
    fun backgroundColor(state: ButtonState): Color
    fun contentColor(state: ButtonState): Color
}