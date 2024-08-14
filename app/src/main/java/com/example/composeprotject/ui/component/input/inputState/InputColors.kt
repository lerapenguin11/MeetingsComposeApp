package com.example.composeprotject.ui.component.input.inputState

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.theme.MeetTheme

class InputColors(
    private val params: ParamsInputColors
) : InputColorsState {
    override fun background(state: InputState): Color {
        return when (state) {
            InputState.SUCCESS -> params.backgroundDefault
            InputState.ERROR -> params.backgroundError
        }
    }
}

object InputColorsDefaults {
    @Composable
    fun colors(
        backgroundError: Color = MeetTheme.colors.error,
        backgroundDefault: Color = MeetTheme.colors.secondary,
    ): InputColors {
        return InputColors(
            ParamsInputColors(
                backgroundDefault = backgroundDefault,
                backgroundError = backgroundError
            )
        )
    }
}

private interface InputColorsState {
    fun background(state: InputState): Color
}