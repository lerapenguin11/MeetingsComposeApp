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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component_old.button.FilledButtonWithProgressBar
import com.example.composeprotject.ui.component_old.custom.PhoneNumberInputOld
import com.example.composeprotject.ui.component_old.state.ButtonState
import com.example.composeprotject.ui.component_old.state.ProgressButtonState
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.auth.AuthPhoneNumberViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    val isLoadingPhoneNumber by authPhoneNumberViewModel.isLoadingSendPhoneNumberFlow().collectAsState()
    var progressState by remember { mutableStateOf(ProgressButtonState.INITIAL) }
    val coroutineScope = rememberCoroutineScope()

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
        PhoneNumberInputOld(
            authPhoneNumberViewModel = authPhoneNumberViewModel,
            onValueChange = { newValue ->
                phoneNumber = newValue
            })
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX69))
        FilledButtonWithProgressBar(
            onClick = {
                when (isValidationPhoneNumber) {
                    true -> {
                        progressState = ProgressButtonState.LOADING
                        authPhoneNumberViewModel.sendPhoneNumberReceiveCode(authPhoneNumberViewModel.getPhoneNumberFlow().value)
                        coroutineScope.launch {
                            delay(DELAY_SEND_PHONE_NUMBER) //TODO
                            if (isLoadingPhoneNumber){
                                progressState = ProgressButtonState.INITIAL
                                onSendCodePhoneNumberClick(authPhoneNumberViewModel.getPhoneNumberFlow().value)
                            }
                        }
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
            buttonText = R.string.text_continue,
            progressState = progressState
        )
    }
}

private const val DELAY_SEND_PHONE_NUMBER = 3000L