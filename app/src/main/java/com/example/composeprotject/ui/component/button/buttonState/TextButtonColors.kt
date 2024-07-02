package com.example.composeprotject.ui.component.button.buttonState

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.theme.MeetTheme

class TextButtonColors(
    private val params : ParamsTextButtonColor
) : TextButtonColorsState {
    override fun backgroundColor(state: ButtonState): Color {
        return when(state){
            ButtonState.INITIAL, ButtonState.HOVER, ButtonState.DISABLED -> params.backgroundColor
        }
    }

    override fun contentColor(state: ButtonState): Color {
        return when(state){
            ButtonState.INITIAL -> params.contentColorActive
            ButtonState.HOVER -> params.contentColorHover
            ButtonState.DISABLED -> params.contentColorDisabled
        }
    }
}

object TextButtonDefaults{
    @Composable
    fun colors(
        backgroundColor: Color = Color.Transparent,
        contentColorActive: Color = MeetTheme.colors.brandDefault,
        contentColorHover: Color = MeetTheme.colors.brandDark,
        contentColorDisabled: Color = MeetTheme.colors.disabledButton
    ): TextButtonColors {
        return TextButtonColors(
            params = ParamsTextButtonColor(
                backgroundColor = backgroundColor,
                contentColorActive = contentColorActive,
                contentColorDisabled = contentColorDisabled,
                contentColorHover = contentColorHover,
            )
        )
    }
}

private interface TextButtonColorsState{
    fun backgroundColor(state : ButtonState): Color
    fun contentColor(state : ButtonState): Color
}