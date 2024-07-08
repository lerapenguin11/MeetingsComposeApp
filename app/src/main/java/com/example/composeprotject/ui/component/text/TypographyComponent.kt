package com.example.composeprotject.ui.component.text

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.composeprotject.ui.theme.MeetTheme

private const val DURATION_OF_EXPANDABLE_TEXT_ANIMATION = 200

@Composable
fun BaseText(
    textColor : Color = MeetTheme.colors.neutralActive,
    textStyle : TextStyle = MeetTheme.typography.bodyText1,
    text : String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier){
    Text(text = text, color = textColor, style = textStyle, textAlign = textAlign, modifier = modifier)
}

@Composable
fun ExpandableText(
    text: String,
    maxLines: Int
) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .animateContentSize(animationSpec = tween(DURATION_OF_EXPANDABLE_TEXT_ANIMATION))
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { isExpanded = !isExpanded }) {
        if (isExpanded) {
            BaseText(
                text = text,
                textStyle = MeetTheme.typography.metadata1,
                textColor = MeetTheme.colors.neutralWeak)
        } else {
            Text(
                text = text,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                color = MeetTheme.colors.neutralWeak,
                style = MeetTheme.typography.metadata1)
        }
    }
}

data class TypographyStyleText(
    val title : String,
    val subtitle : String,
    val titleTextStyle : TextStyle,
    val subtitleTextStyle : TextStyle,
    val textStyle : TextStyle
)