package com.example.composeprotject.ui.component.button.buttonStyle

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class ParamsButtonColor(
    val backgroundColorPrimary: Brush,
    val backgroundColorSecondary: Brush,
    val backgroundColorDisabled: Brush,
    val contentColorPrimary: Color,
    val contentColorSecondary: Color,
    val contentColorDisabled: Color
)
