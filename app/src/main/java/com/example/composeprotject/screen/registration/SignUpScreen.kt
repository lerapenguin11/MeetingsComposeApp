package com.example.composeprotject.screen.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.screen.state.RegistrationScreenState
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.input.SimpleInputField
import com.example.composeprotject.ui.component.phoneInput.PhoneNumberContainer
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.eventDate
import com.example.composeprotject.ui.theme.MeetTheme

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(
        contentPadding = PaddingValues()
    )
}

@Composable
fun SignUpScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val screenState by remember { mutableStateOf(RegistrationScreenState.INPUT_NAME) }
    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX16),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .weight(5f)
        ) {
            SpacerHeight(height = MeetTheme.sizes.sizeX20)
            SignTopBar()
            SpacerHeight(height = MeetTheme.sizes.sizeX12)
            DescriptionBlock(
                eventName = "Супертестировщики",
                startDate = 1725021117,
                shortAddress = "Невский проспект, 11"
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX24)
            InputBlock(
                screenState = screenState,
                isEnabled = true
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionComponent(screenState = screenState)
            SpacerHeight(height = 28.dp)
        }
    }
}

@Composable
private fun ActionComponent(
    screenState: RegistrationScreenState
) {
    if (screenState == RegistrationScreenState.INPUT_CODE) {
        Text(
            text = "Получить новый код через 10",
            color = MeetTheme.colors.darkGray,
            style = MeetTheme.typography.interMedium14
        )
        SpacerHeight(height = MeetTheme.sizes.sizeX24)
    }
    FilledButton(
        state = FilledButtonState.ACTIVE_PRIMARY,
        buttonText = variantTextButton(screenState = screenState)
    ) {

    }
}

@Composable
private fun variantTextButton(
    screenState: RegistrationScreenState
): String {
    return when (screenState) {
        RegistrationScreenState.INPUT_NAME -> {
            stringResource(id = CommonString.text_continue)
        }

        RegistrationScreenState.INPUT_CODE -> {
            stringResource(R.string.text_send_and_confirm_an_entry)
        }

        RegistrationScreenState.INPUT_NUMBER_PHONE -> {
            stringResource(R.string.text_get_code)
        }
    }
}

@Composable
fun InputBlock(
    screenState: RegistrationScreenState,
    isEnabled: Boolean
) {
    when (screenState) {
        RegistrationScreenState.INPUT_NAME -> {
            SimpleInputField(
                textPlaceholder = stringResource(R.string.text_name_and_surname),
                isEnabled = isEnabled,
                state = InputState.SUCCESS,
                onValueChange = {
                    //TODO
                }
            )
        }

        RegistrationScreenState.INPUT_CODE -> {
            SimpleInputField(
                textPlaceholder = stringResource(R.string.text_placeholder_code),
                isEnabled = isEnabled,
                state = InputState.SUCCESS,
                onValueChange = {
                    //TODO
                }
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX8)
            Text(
                text = "Отправили код на +7 999 999-99-99",
                color = MeetTheme.colors.darkGray,
                style = MeetTheme.typography.interMedium14
            )
        }

        RegistrationScreenState.INPUT_NUMBER_PHONE -> {
            PhoneNumberContainer()
        }
    }
}

@Composable
private fun DescriptionBlock(
    eventName: String,
    startDate: Long,
    shortAddress: String
) {
    Text(
        text = "$eventName · ${eventDate(timestamp = startDate)} · $shortAddress",
        color = Color.Black,
        style = MeetTheme.typography.interRegular19
    )
}

@Composable
private fun SignTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .weight(5f),
            text = stringResource(R.string.text_log_in_and_make_appointment),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold49
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = MeetTheme.sizes.sizeX10))
                .clickable {
                    //TODO
                }
        ) {
            Image(
                modifier = Modifier
                    .padding(
                        horizontal = MeetTheme.sizes.sizeX5,
                        vertical = MeetTheme.sizes.sizeX4
                    ),
                painter = painterResource(id = CommonDrawables.ic_close_bt),
                contentDescription = null
            )
        }
    }
}
