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
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.button.TextButton
import com.example.composeprotject.ui.component.custom.CodeInput
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.AuthPhoneNumberViewModel
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun VerificationCodeScreen(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    contentPadding: PaddingValues,
    authPhoneNumberViewModel: AuthPhoneNumberViewModel,
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    mainViewModel.setCurrentScreen(
        screen = NavItem.VerificationCodeScreenItem,
        showBottomBar = false,
        showTopBar = true
    )

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
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX8))
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
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX49))
        CodeInput(viewModel = authPhoneNumberViewModel)
        val inputValue = authPhoneNumberViewModel.code.collectAsState()
        if (inputValue.value.length == VERIFICATION_CODE_LENGTH) {
            navController.navigate(NavItem.CreateProfileScreenItem.route)
        }
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX69))
        TextButton(onClick = { /*TODO*/}, buttonText = R.string.text_request_code_again)
    }
}

private const val VERIFICATION_CODE_LENGTH = 4