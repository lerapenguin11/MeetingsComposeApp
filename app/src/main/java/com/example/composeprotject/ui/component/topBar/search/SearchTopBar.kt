package com.example.composeprotject.ui.component.topBar.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.composeprotject.screen.state.SearchState
import com.example.composeprotject.ui.component.input.inputState.InputColors
import com.example.composeprotject.ui.component.input.inputState.InputColorsDefaults
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.theme.MeetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String?,
    isEnabled: Boolean,
    searchState: SearchState,
    state: InputState,
    authToken: String?,
    modifier: Modifier = Modifier,
    inputColors: InputColors = InputColorsDefaults.colors(),
    onValueChange: (String) -> Unit,
    onMainScreenState: (SearchState) -> Unit,
    onGoProfile: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val interactionSource = remember { MutableInteractionSource() }
    val singleLine = true

    val colors =
        OutlinedTextFieldDefaults.colors(
            errorContainerColor = inputColors.background(state),
            errorBorderColor = inputColors.background(state),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = MeetTheme.colors.secondary,
            focusedContainerColor = MeetTheme.colors.secondary,
            focusedTextColor = MeetTheme.colors.black,
            unfocusedTextColor = MeetTheme.colors.black,
            errorTextColor = MeetTheme.colors.black,
            cursorColor = MeetTheme.colors.primary
        )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 0.dp,
            topEnd = 0.dp
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = MeetTheme.sizes.sizeX5
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = searchText ?: "",
                onValueChange = { newValue ->
                    //searchText = newValue
                    onValueChange(newValue)
                },
                modifier = modifier
                    .weight(5f)
                    .fillMaxWidth(),
                interactionSource = interactionSource,
                enabled = isEnabled,
                singleLine = singleLine,
                textStyle = MeetTheme.typography.interMedium14,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                ),
                cursorBrush = Brush.verticalGradient(
                    0.00f to MeetTheme.colors.primary,
                    0.05f to MeetTheme.colors.primary
                )
            ) {
                OutlinedTextFieldDefaults.DecorationBox(
                    value = searchText.orEmpty(),
                    visualTransformation = VisualTransformation.None,
                    innerTextField = it,
                    singleLine = singleLine,
                    enabled = isEnabled,
                    placeholder = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier
                                    .size(size = 22.dp),
                                tint = MeetTheme.colors.darkGray,
                                painter = painterResource(id = CommonDrawables.ic_event_community_search),
                                contentDescription = stringResource(id = CommonString.text_search)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = stringResource(id = CommonString.text_search),
                                style = MeetTheme.typography.interMedium14,
                                color = MeetTheme.colors.darkGray
                            )
                        }
                    },
                    interactionSource = interactionSource,
                    contentPadding =
                    OutlinedTextFieldDefaults.contentPadding(
                        top = 11.dp,
                        bottom = 11.dp,
                        start = 10.dp,
                        end = 10.dp
                    ),
                    colors = colors,
                    container = {
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = isEnabled,
                            isError = state == InputState.ERROR,
                            colors = colors,
                            interactionSource = interactionSource,
                            shape = RoundedCornerShape(MeetTheme.sizes.sizeX16)
                        )
                    },
                    trailingIcon = {
                        AnimatedVisibility(visible = searchText.orEmpty().isNotEmpty()) {
                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        //searchText = ""
                                        onValueChange("")
                                    },
                                tint = MeetTheme.colors.darkGray,
                                painter = painterResource(id = CommonDrawables.ic_event_text_clear),
                                contentDescription = stringResource(id = CommonString.text_search)
                            )
                        }
                    }

                )
            }
            if (authToken != null) {
                Spacer(modifier = Modifier.width(MeetTheme.sizes.sizeX8))
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true),
                            onClick = {
                                onGoProfile()
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (searchText.orEmpty()
                            .isEmpty() && searchState == SearchState.MAIN_DEFAULT_SCREEN
                    ) {
                        Box(
                            modifier = Modifier
                                .width(width = 39.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Image(
                                modifier = Modifier.size(width = 32.dp, height = 44.dp),
                                painter = painterResource(id = CommonDrawables.ic_user_profile),
                                contentDescription = null
                            )
                        }
                    } else {
                        onMainScreenState(SearchState.MAIN_SEARCH_SCREEN)
                        ActionCancellation(
                            onClickCancel = {
                                onMainScreenState(it)
                            },
                            onClearText = {
                                onValueChange(it)
                            }
                        )
                    }
                }
            } else {
                if (searchText.orEmpty().isNotEmpty()) {
                    Spacer(modifier = Modifier.width(MeetTheme.sizes.sizeX8))
                    ActionCancellation(
                        onClickCancel = {
                            onMainScreenState(it)
                        },
                        onClearText = {
                            onValueChange(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionCancellation(
    modifier: Modifier = Modifier,
    onClickCancel: (SearchState) -> Unit,
    onClearText: (String) -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Text(
            modifier = modifier
                .clickable {
                    onClearText("")
                    onClickCancel(SearchState.MAIN_DEFAULT_SCREEN)
                },
            text = stringResource(CommonString.text_cancel),
            color = MeetTheme.colors.primary,
            style = MeetTheme.typography.interSemiBold14
        )
    }
}