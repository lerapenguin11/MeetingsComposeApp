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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.composeprotject.R
import com.example.composeprotject.ui.component_old.button.TextButton
import com.example.composeprotject.ui.component_old.custom.CodeInput
import com.example.composeprotject.ui.component_old.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.auth.AuthCodeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerificationCodeScreen(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    contentPadding: PaddingValues,
    codeViewModel: AuthCodeViewModel = koinViewModel(),
    onCreateProfile: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BaseText(
            text = stringResource(id = R.string.text_valid_code_title),
            textColor = MeetTheme.colors.neutralActive,
            textStyle = MeetTheme.typography.heading2
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX8))
        BaseText(
            text = stringResource(id = R.string.text_valid_code_header),
            textColor = MeetTheme.colors.neutralActive,
            textStyle = MeetTheme.typography.bodyText2
        )
        BaseText(
            text = phoneNumber,
            textColor = MeetTheme.colors.neutralActive,
            textStyle = MeetTheme.typography.bodyText2
        )
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX49))
        CodeInput(viewModel = codeViewModel)
        val inputValue = codeViewModel.getCodeFlow().collectAsState()
        if (inputValue.value.length == VERIFICATION_CODE_LENGTH) {
            onCreateProfile()
        }
        Spacer(modifier = Modifier.height(MeetTheme.sizes.sizeX69))
        TextButton(onClick = { /*TODO*/}, buttonText = R.string.text_request_code_again)
    }
}

private const val VERIFICATION_CODE_LENGTH = 4