package com.example.composeprotject.ui.component_old.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.auth.AuthCodeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeInput(
    modifier: Modifier = Modifier,
    viewModel: AuthCodeViewModel
) {
    val interactionSource = remember { MutableInteractionSource() }
    val value by remember { mutableStateOf(viewModel.getCodeFlow().value) }
    val codeValue by viewModel.getCodeFlow().collectAsState()

    Box(modifier = modifier.padding(MeetTheme.sizes.sizeX10)) {
        BasicTextField(
            modifier = Modifier
                .width(240.dp),
            textStyle = TextStyle(
                letterSpacing = 40.sp,
                fontFamily = MeetTheme.typography.heading1.fontFamily,
                textAlign = TextAlign.Start,
                fontSize = 32.sp
            ),
            cursorBrush = SolidColor(Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = codeValue,
            onValueChange = { if (it.length <= MAX_LENGTH_CODE) viewModel.setCode(it) },
        ) {
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = OutlinedTextFieldDefaults.contentPadding(
                    top = 0.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp,
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    focusedPrefixColor = Color.Transparent,
                    focusedSuffixColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Transparent
                )
            )
        }

        for (i in ZERO..(MAX_CODE_INPUT_CHAR_INDEX - codeValue.length)) {
            val offsetLength =
                INDENTATION_INSIDE_CONTAINER + ((CHAR_WIDTH + INDENTATION_BETWEEN_POINTS) * i)

            Box(
                modifier = Modifier
                    .offset(-offsetLength.dp)
                    .width(MeetTheme.sizes.sizeX32)
                    .height(MeetTheme.sizes.sizeX40)
                    .background(Color.White)
                    .align(Alignment.CenterEnd)
            ) {
                Box(
                    modifier = Modifier
                        .size(MeetTheme.sizes.sizeX24)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                        .background(color = MeetTheme.colors.neutralLine)
                )
            }
        }
    }
}

private const val MAX_CODE_INPUT_CHAR_INDEX = 3
private const val ZERO = 0
private const val INDENTATION_BETWEEN_POINTS = 25
private const val MAX_LENGTH_CODE = 4
private const val CHAR_WIDTH = 32
private const val INDENTATION_INSIDE_CONTAINER = 15