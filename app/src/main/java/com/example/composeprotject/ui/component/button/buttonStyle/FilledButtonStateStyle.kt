package com.example.composeprotject.ui.component.button.buttonStyle

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.theme.MeetTheme

class FilledButtonStateStyle(private val paramsFilledButtonColor: ParamsFilledButtonColor) :
    FilledButtonStyle {
    override fun backgroundColor(state: FilledButtonState): Brush {
        return when (state) {
            FilledButtonState.ACTIVE_PRIMARY -> paramsFilledButtonColor.backgroundColorPrimary
            FilledButtonState.ACTIVE_SECONDARY -> paramsFilledButtonColor.backgroundColorSecondary
            FilledButtonState.LOADING -> paramsFilledButtonColor.backgroundColorPrimary
            FilledButtonState.DISABLED -> paramsFilledButtonColor.backgroundColorDisabled
        }
    }

    override fun contentColor(state: FilledButtonState): Color {
        return when (state) {
            FilledButtonState.ACTIVE_PRIMARY -> paramsFilledButtonColor.contentColorPrimary
            FilledButtonState.ACTIVE_SECONDARY -> paramsFilledButtonColor.contentColorSecondary
            FilledButtonState.LOADING -> paramsFilledButtonColor.contentColorPrimary
            FilledButtonState.DISABLED -> paramsFilledButtonColor.contentColorDisabled
        }
    }
}

object FilledButtonColorsDefault {
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
    ): FilledButtonStateStyle {
        return FilledButtonStateStyle(
            paramsFilledButtonColor = ParamsFilledButtonColor(
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

private interface FilledButtonStyle {
    fun backgroundColor(state: FilledButtonState): Brush
    fun contentColor(state: FilledButtonState): Color
}