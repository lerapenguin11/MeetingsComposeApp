package com.example.composeprotject.ui.component.input

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.input.inputState.InputColors
import com.example.composeprotject.ui.component.input.inputState.InputColorsDefaults
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.theme.MeetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchOutlinedTextFieldIconOld(
    modifier: Modifier = Modifier,
    textPlaceholder: String,
    isEnabled: Boolean
) {
    var searchText by remember { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }
    val colorBorder =
        if (searchText.isNotEmpty()) MeetTheme.colors.neutralOffWhite else MeetTheme.colors.neutralLine
    val colorContent =
        if (searchText.isNotEmpty()) MeetTheme.colors.neutralActive else MeetTheme.colors.neutralDisabled
    val singleLine = true

    val colors =
        OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorBorder,
            unfocusedBorderColor = MeetTheme.colors.neutralOffWhite,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = MeetTheme.colors.neutralOffWhite,
            focusedContainerColor = MeetTheme.colors.neutralOffWhite,
            disabledContainerColor = MeetTheme.colors.neutralOffWhiteDisabled,
            focusedTextColor = MeetTheme.colors.neutralActive,
            disabledLeadingIconColor = MeetTheme.colors.neutralDisabled2,
            focusedLeadingIconColor = colorContent,
            unfocusedLeadingIconColor = colorContent,
        )
    BasicTextField(
        value = searchText,
        onValueChange = { searchText = it },
        modifier = modifier
            .border(0.dp, Color.Transparent)
            .height(36.dp)
            .fillMaxWidth(),
        interactionSource = interactionSource,
        enabled = isEnabled,
        singleLine = singleLine,
        textStyle = MeetTheme.typography.bodyText1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = searchText,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = singleLine,
            enabled = isEnabled,
            placeholder = {
                Text(
                    text = textPlaceholder,
                    style = MeetTheme.typography.bodyText1,
                    color = if (isEnabled) MeetTheme.colors.neutralDisabled else MeetTheme.colors.neutralDisabled2
                )
            },
            interactionSource = interactionSource,
            contentPadding =
            OutlinedTextFieldDefaults.contentPadding(
                top = MeetTheme.sizes.sizeX6,
                bottom = MeetTheme.sizes.sizeX6,
                start = MeetTheme.sizes.sizeX8,
                end = MeetTheme.sizes.sizeX8
            ),
            colors = colors,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = isEnabled,
                    isError = false,
                    colors = colors,
                    interactionSource = interactionSource,
                    shape = RoundedCornerShape(4.dp),
                    focusedBorderThickness = 2.dp
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.text_search)
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextFieldOld(
    textPlaceholder: String,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    var inputText by remember { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }
    val colorBorder =
        if (inputText.isNotEmpty()) MeetTheme.colors.neutralOffWhite else MeetTheme.colors.neutralLine
    val colorContent =
        if (inputText.isNotEmpty()) MeetTheme.colors.neutralActive else MeetTheme.colors.neutralDisabled
    val singleLine = true

    val colors =
        OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorBorder,
            unfocusedBorderColor = MeetTheme.colors.neutralOffWhite,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = MeetTheme.colors.neutralOffWhite,
            focusedContainerColor = MeetTheme.colors.neutralOffWhite,
            disabledContainerColor = MeetTheme.colors.neutralOffWhiteDisabled,
            focusedTextColor = MeetTheme.colors.neutralActive,
            disabledLeadingIconColor = MeetTheme.colors.neutralDisabled2,
            focusedLeadingIconColor = colorContent,
            unfocusedLeadingIconColor = colorContent,
        )
    BasicTextField(
        value = inputText,
        onValueChange = { newValue ->
            inputText = newValue
            onValueChange(newValue)
        },
        modifier = modifier
            .border(0.dp, Color.Transparent)
            .height(36.dp)
            .fillMaxWidth(),
        interactionSource = interactionSource,
        enabled = isEnabled,
        singleLine = singleLine,
        textStyle = MeetTheme.typography.bodyText1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
                    style = MeetTheme.typography.bodyText1,
                    color = if (isEnabled) MeetTheme.colors.neutralDisabled else MeetTheme.colors.neutralDisabled2
                )
            },
            interactionSource = interactionSource,
            contentPadding =
            OutlinedTextFieldDefaults.contentPadding(
                top = MeetTheme.sizes.sizeX6,
                bottom = MeetTheme.sizes.sizeX6,
                start = MeetTheme.sizes.sizeX8,
                end = MeetTheme.sizes.sizeX8
            ),
            colors = colors,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = isEnabled,
                    isError = false,
                    colors = colors,
                    interactionSource = interactionSource,
                    shape = RoundedCornerShape(4.dp),
                    focusedBorderThickness = 2.dp
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleInputField(
    textPlaceholder: String,
    isEnabled: Boolean,
    state: InputState,
    modifier: Modifier = Modifier,
    inputColors: InputColors = InputColorsDefaults.colors(),
    onValueChange: (String) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val interactionSource = remember { MutableInteractionSource() }
    val singleLine = true

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
            inputText = newValue
            onValueChange(newValue)
        },
        modifier = modifier
            .fillMaxWidth(),
        interactionSource = interactionSource,
        enabled = isEnabled,
        singleLine = singleLine,
        textStyle = MeetTheme.typography.interRegular19,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
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
                bottom = MeetTheme.sizes.sizeX17,
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