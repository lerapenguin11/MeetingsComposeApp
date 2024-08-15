package com.example.composeprotject.ui.component.button.buttonStyle

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.theme.MeetTheme

class ButtonStyle(private val paramsButtonColor: ParamsButtonColor) : ButtonStateStyle {
    override fun backgroundColor(state: ButtonState): Brush {
        return when (state) {
            ButtonState.ACTIVE_PRIMARY -> paramsButtonColor.backgroundColorPrimary
            ButtonState.ACTIVE_SECONDARY -> paramsButtonColor.backgroundColorSecondary
            ButtonState.LOADING -> paramsButtonColor.backgroundColorPrimary
            ButtonState.DISABLED -> paramsButtonColor.backgroundColorDisabled
        }
    }

    override fun contentColor(state: ButtonState): Color {
        return when (state) {
            ButtonState.ACTIVE_PRIMARY -> paramsButtonColor.contentColorPrimary
            ButtonState.ACTIVE_SECONDARY -> paramsButtonColor.contentColorSecondary
            ButtonState.LOADING -> paramsButtonColor.contentColorPrimary
            ButtonState.DISABLED -> paramsButtonColor.contentColorDisabled
        }
    }
}

object ButtonColorsDefault {
    @Composable
    fun colors(
        contentColorPrimary: Color = MeetTheme.colors.neutralWhite,
        contentColorSecondary: Color = MeetTheme.colors.primary,
        contentColorDisabled: Color = MeetTheme.colors.gray,
        backgroundColorPrimary: Brush = MeetTheme.colors.backgroundColorPrimary,
        backgroundColorSecondary: Brush = MeetTheme.colors.backgroundColorSecondary,
        backgroundColorDisabled: Brush = Brush.horizontalGradient(
            listOf(
                Color(0xffF6F6FA), Color(0xffF6F6FA),
                Color(0xffF6F6FA), Color(0xffF6F6FA),
                Color(0xffF6F6FA), Color(0xffF6F6FA),
                Color(0xffF6F6FA), Color(0xffF6F6FA)
            )
        )
    ): ButtonStyle {
        return ButtonStyle(
            paramsButtonColor = ParamsButtonColor(
                contentColorSecondary = contentColorSecondary,
                contentColorDisabled = contentColorDisabled,
                contentColorPrimary = contentColorPrimary,
                backgroundColorDisabled = backgroundColorDisabled,
                backgroundColorPrimary = backgroundColorPrimary,
                backgroundColorSecondary = backgroundColorSecondary
            )
        )
    }
}

private interface ButtonStateStyle {
    fun backgroundColor(state: ButtonState): Brush
    fun contentColor(state: ButtonState): Color
}