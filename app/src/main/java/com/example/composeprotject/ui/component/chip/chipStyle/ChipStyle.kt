package com.example.composeprotject.ui.component.chip.chipStyle

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.example.composeprotject.ui.theme.MeetTheme

class ChipStyle(
    private val paramsChip: ParamsChip
) : ChipStyleVariant {
    override fun chipSize(variant: ChipSize): Map<String, Dp> {
        return when (variant) {
            ChipSize.BIG -> paramsChip.bigPadding
            ChipSize.SMALL -> paramsChip.smallPadding
            ChipSize.MEDIUM -> paramsChip.mediumPadding
        }
    }

    override fun chipSelectedColor(variant: ChipClick): Map<String, Color> {
        return when (variant) {
            ChipClick.TRUE -> paramsChip.selectedColor
            ChipClick.FALSE -> paramsChip.notSelectedColor
        }
    }

    override fun chipTextStyle(variant: ChipSize): TextStyle {
        return when (variant) {
            ChipSize.BIG -> paramsChip.bigTextStyle
            ChipSize.SMALL -> paramsChip.smallTextStyle
            ChipSize.MEDIUM -> paramsChip.mediumTextStyle
        }
    }
}

object ChipStyleDefault {
    @Composable
    fun chipStyle(
        smallPadding: Map<String, Dp> = mapOf(
            HORIZONTAL_PADDING_KEY to MeetTheme.sizes.sizeX6,
            VERTICAL_PADDING_KEY to MeetTheme.sizes.sizeX3
        ),
        mediumPadding: Map<String, Dp> = mapOf(
            HORIZONTAL_PADDING_KEY to MeetTheme.sizes.sizeX8,
            VERTICAL_PADDING_KEY to MeetTheme.sizes.sizeX8
        ),
        bigPadding: Map<String, Dp> = mapOf(
            HORIZONTAL_PADDING_KEY to MeetTheme.sizes.sizeX12,
            VERTICAL_PADDING_KEY to MeetTheme.sizes.sizeX10
        ),
        selectedColor: Map<String, Color> = mapOf(
            SELECTED_BOX_COLOR to MeetTheme.colors.primary,
            SELECTED_TEXT_COLOR to MeetTheme.colors.neutralWhite
        ),
        notSelectedColor: Map<String, Color> = mapOf(
            NOT_SELECTED_BOX_COLOR to MeetTheme.colors.secondary,
            NOT_SELECTED_TEXT_COLOR to MeetTheme.colors.primary
        ),
        smallTextStyle: TextStyle = MeetTheme.typography.interMedium14,
        mediumTextStyle: TextStyle = MeetTheme.typography.interMedium16,
        bigTextStyle: TextStyle = MeetTheme.typography.interMedium22
    ): ChipStyle {
        return ChipStyle(
            paramsChip = ParamsChip(
                smallPadding = smallPadding,
                mediumPadding = mediumPadding,
                bigPadding = bigPadding,
                selectedColor = selectedColor,
                notSelectedColor = notSelectedColor,
                smallTextStyle = smallTextStyle,
                mediumTextStyle = mediumTextStyle,
                bigTextStyle = bigTextStyle
            )
        )
    }
}

private interface ChipStyleVariant {
    fun chipSize(variant: ChipSize): Map<String, Dp>
    fun chipSelectedColor(variant: ChipClick): Map<String, Color>
    fun chipTextStyle(variant: ChipSize): TextStyle
}

private const val HORIZONTAL_PADDING_KEY = "horizontal_padding_key"
private const val VERTICAL_PADDING_KEY = "vertical_padding_key"
private const val SELECTED_TEXT_COLOR = "selected_text_color"
private const val SELECTED_BOX_COLOR = "selected_box_color"
private const val NOT_SELECTED_TEXT_COLOR = "selected_text_color"
private const val NOT_SELECTED_BOX_COLOR = "selected_box_color"