package com.example.composeprotject.ui.component.chip.chipStyle

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class ParamsChip(
    val bigPadding: Map<String, Dp>,
    val smallPadding: Map<String, Dp>,
    val mediumPadding: Map<String, Dp>,
    val selectedColor: Map<String, Color>,
    val notSelectedColor: Map<String, Color>,
    val bigTextStyle: TextStyle,
    val mediumTextStyle: TextStyle,
    val smallTextStyle: TextStyle
)

enum class ChipSize {
    BIG, SMALL, MEDIUM
}

enum class ChipSelect {
    TRUE,
    FALSE
}

enum class ChipClick {
    ON_CLICK,
    NOT_ON_CLICK
}



