package com.example.composeprotject.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.composeprotject.ui.component.chip.chipStyle.ChipClick
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSelect
import com.example.composeprotject.ui.component.chip.chipStyle.ChipSize
import com.example.composeprotject.ui.component.chip.chipStyle.ChipStyle
import com.example.composeprotject.ui.component.chip.chipStyle.ChipStyleDefault
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun Chip(
    text: String,
    chipSize: ChipSize,
    chipColors: ChipSelect,
    chipClick: ChipClick,
    modifier: Modifier = Modifier,
    style: ChipStyle = ChipStyleDefault.chipStyle(),
    onClick: () -> Unit
) {
    val padding = style.chipSize(variant = chipSize)
    var selected by remember { mutableStateOf(chipColors) }
    val color = style.chipSelectedColor(variant = selected)
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(MeetTheme.sizes.sizeX8))
            .background(color = color[SELECTED_BOX_COLOR] ?: MeetTheme.colors.secondary)
            .then(
                if (chipClick == ChipClick.ON_CLICK) {
                    modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            selected =
                                if (chipColors == ChipSelect.TRUE) ChipSelect.FALSE else ChipSelect.TRUE
                            onClick()
                        }
                    )
                } else {
                    modifier
                }
            )
            .padding(
                horizontal = padding[HORIZONTAL_PADDING_KEY] ?: MeetTheme.sizes.sizeX6,
                vertical = padding[VERTICAL_PADDING_KEY] ?: MeetTheme.sizes.sizeX3
            )
    ) {
        BaseText(
            text = text,
            textStyle = style.chipTextStyle(chipSize),
            textColor = color[SELECTED_TEXT_COLOR] ?: MeetTheme.colors.primary
        )
    }
}

private const val HORIZONTAL_PADDING_KEY = "horizontal_padding_key"
private const val VERTICAL_PADDING_KEY = "vertical_padding_key"
private const val SELECTED_TEXT_COLOR = "selected_text_color"
private const val SELECTED_BOX_COLOR = "selected_box_color"
private const val NOT_SELECTED_TEXT_COLOR = "selected_text_color"
private const val NOT_SELECTED_BOX_COLOR = "selected_box_color"