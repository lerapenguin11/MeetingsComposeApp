package com.example.composeprotject.screen.verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.custom.PhoneNumberInput
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.auth.AuthPhoneNumberViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerifInputPhoneNumberScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    authPhoneNumberViewModel: AuthPhoneNumberViewModel = koinViewModel(),
    onSendCodePhoneNumberClick: (String) -> Unit
) {
    val activeAuthButton by authPhoneNumberViewModel.getActiveAuthButton().collectAsState()
    val isValidationPhoneNumber by authPhoneNumberViewModel.getValidationPhoneNumberFlow()
        .collectAsState()
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = MeetTheme.sizes.sizeX24)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BaseText(
            text = stringResource(id = R.string.text_enter_phone_number),
            textColor = MeetTheme.colors.neutralActive,
            textStyle = MeetTheme.typography.heading2
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        BaseText(
            text = stringResource(id = R.string.text_desc_code),
            textColor = MeetTheme.colors.neutralActive,
            textStyle = MeetTheme.typography.bodyText2,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX49))
        PhoneNumberInput(
            authPhoneNumberViewModel = authPhoneNumberViewModel,
            onValueChange = { newValue ->
                phoneNumber = newValue
            })
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX69))
        FilledButton(
            onClick = {

                when (isValidationPhoneNumber) {
                    true -> {
                        onSendCodePhoneNumberClick(authPhoneNumberViewModel.getPhoneNumberFlow().value)
                    }

                    false -> {
                        /*TODO: докинуть логики*/
                    }
                }
            },
            state = when (activeAuthButton) {
                true -> ButtonState.INITIAL
                false -> ButtonState.DISABLED
            },
            buttonText = R.string.text_continue
        )
    }
}