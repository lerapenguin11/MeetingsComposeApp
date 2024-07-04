package com.example.composeprotject.ui.component.button.buttonState

import androidx.compose.ui.graphics.Color

data class ParamsFilledButtonColor(
    val backgroundColorActive: Color,
    val backgroundColorHover : Color,
    val backgroundColorDisabled : Color,
    val contentColorActive: Color,
    val contentColorHover: Color,
    val contentColorDisabled: Color
)

data class ParamsOutlinedButtonColor(
    val backgroundColor : Color,
    val contentColorActive: Color,
    val contentColorHover: Color,
    val contentColorDisabled: Color,
    val strokeColorActive : Color,
    val strokeColorHover : Color,
    val strokeColorDisabled : Color
)

data class ParamsTextButtonColor(
    val backgroundColor : Color,
    val contentColorActive: Color,
    val contentColorHover: Color,
    val contentColorDisabled: Color
)