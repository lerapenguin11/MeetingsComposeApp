package com.example.composeprotject.ui.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.composeprotject.ui.theme.MeetTheme


@Composable
fun BaseText(
    textColor : Color = MeetTheme.colors.neutralActive,
    textStyle : TextStyle = MeetTheme.typography.bodyText1,
    text : String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier){
    Text(text = text, color = textColor, style = textStyle, textAlign = textAlign, modifier = modifier)
}

data class TypographyStyleText(
    val title : String,
    val subtitle : String,
    val titleTextStyle : TextStyle,
    val subtitleTextStyle : TextStyle,
    val textStyle : TextStyle
)