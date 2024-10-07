package com.example.composeprotject.ui.component.input

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.example.composeprotject.ui.component.input.inputState.InputColors
import com.example.composeprotject.ui.component.input.inputState.InputColorsDefaults
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.theme.MeetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleInputField(
    textPlaceholder: String,
    isEnabled: Boolean,
    state: InputState,
    inputText: String,
    modifier: Modifier = Modifier,
    limit: Int? = null,
    maxLine: Int = 1,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    inputColors: InputColors = InputColorsDefaults.colors(),
    onValueChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var textOverflow by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    val colors =
        OutlinedTextFieldDefaults.colors(
            errorContainerColor = inputColors.background(state),
            errorBorderColor = inputColors.background(state),
            focusedBorderColor = MeetTheme.colors.primary,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = MeetTheme.colors.secondary,
            focusedContainerColor = MeetTheme.colors.secondary,
            focusedTextColor = MeetTheme.colors.black,
            unfocusedTextColor = MeetTheme.colors.black,
            errorTextColor = MeetTheme.colors.black
        )
    BasicTextField(
        value = inputText,
        onValueChange = { newValue ->
            val value = if (limit == null) newValue else newValue.take(limit)
            onValueChange(value)
        },
        onTextLayout = { textLayoutResult ->
            textOverflow = textLayoutResult.hasVisualOverflow
        },
        modifier = modifier
            .fillMaxWidth(),
        maxLines = if (singleLine) maxLine else checkMaxLine(textOverflow, maxLine),
        minLines = if (singleLine) maxLine else checkMaxLine(textOverflow, maxLine),
        interactionSource = interactionSource,
        enabled = isEnabled,
        singleLine = singleLine,
        textStyle = MeetTheme.typography.interRegular19,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        )
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = inputText,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = singleLine,
            enabled = isEnabled,
            placeholder = {
                Text(
                    text = textPlaceholder,
                    style = MeetTheme.typography.interRegular19,
                    color = MeetTheme.colors.neutralDisabled
                )
            },
            interactionSource = interactionSource,
            contentPadding =
            OutlinedTextFieldDefaults.contentPadding(
                top = MeetTheme.sizes.sizeX16,
                bottom = MeetTheme.sizes.sizeX16,
                start = MeetTheme.sizes.sizeX20,
                end = MeetTheme.sizes.sizeX20
            ),
            colors = colors,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = isEnabled,
                    isError = state == InputState.ERROR,
                    colors = colors,
                    interactionSource = interactionSource,
                    shape = RoundedCornerShape(MeetTheme.sizes.sizeX16),
                    focusedBorderThickness = MeetTheme.sizes.sizeX1
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFieldIcon(
    inputText: String,
    textPlaceholder: String,
    isEnabled: Boolean,
    state: InputState,
    leadingIcon: Int,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    inputColors: InputColors = InputColorsDefaults.colors(),
    onValueChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val interactionSource = remember { MutableInteractionSource() }

    val colors =
        OutlinedTextFieldDefaults.colors(
            errorContainerColor = inputColors.background(state),
            errorBorderColor = inputColors.background(state),
            focusedBorderColor = MeetTheme.colors.primary,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = MeetTheme.colors.secondary,
            focusedContainerColor = MeetTheme.colors.secondary,
            focusedTextColor = MeetTheme.colors.black,
            unfocusedTextColor = MeetTheme.colors.black,
            errorTextColor = MeetTheme.colors.black
        )
    BasicTextField(
        value = inputText,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        modifier = modifier
            .fillMaxWidth(),
        interactionSource = interactionSource,
        enabled = isEnabled,
        singleLine = singleLine,
        textStyle = MeetTheme.typography.interRegular19,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        )
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = inputText,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = singleLine,
            enabled = isEnabled,
            leadingIcon = {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = textPlaceholder,
                    style = MeetTheme.typography.interRegular19,
                    color = MeetTheme.colors.neutralDisabled
                )
            },
            interactionSource = interactionSource,
            contentPadding =
            OutlinedTextFieldDefaults.contentPadding(
                top = MeetTheme.sizes.sizeX16,
                bottom = MeetTheme.sizes.sizeX16,
                start = MeetTheme.sizes.sizeX20,
                end = MeetTheme.sizes.sizeX20
            ),
            colors = colors,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = isEnabled,
                    isError = state == InputState.ERROR,
                    colors = colors,
                    interactionSource = interactionSource,
                    shape = RoundedCornerShape(MeetTheme.sizes.sizeX16),
                    focusedBorderThickness = MeetTheme.sizes.sizeX1
                )
            }
        )
    }
}

private fun checkMaxLine(textOverflow: Boolean, maxLine: Int): Int {
    return if (textOverflow) {
        Int.MAX_VALUE
    } else {
        maxLine
    }
}
