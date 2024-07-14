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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.composeprotject.R
import com.example.composeprotject.navigation.NavItem
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.custom.PhoneNumberInput
import com.example.composeprotject.ui.component.state.ButtonState
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.AuthViewModel
import com.example.composeprotject.viewModel.MainViewModel

@Composable
fun VerifInputPhoneNumberScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel
) {
    mainViewModel.setCurrentScreen(
        screen = NavItem.VerifInputPhoneNumberScreenItem,
        showBottomBar = false,
        showTopBar = true
    )
    val activeAuthButton by authViewModel.activeAuthButton.collectAsState()
    val isValidationPhoneNumber by authViewModel.validationPhoneNumber.collectAsState()

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
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX8))
        BaseText(
            text = stringResource(id = R.string.text_desc_code),
            textColor = MeetTheme.colors.neutralActive,
            textStyle = MeetTheme.typography.bodyText2,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX49))
        PhoneNumberInput(authViewModel = authViewModel)
        Spacer(modifier = modifier.height(MeetTheme.sizes.sizeX69))
        FilledButton(
            onClick = {
                if (isValidationPhoneNumber) {
                    navController.navigate(route = NavItem.VerificationCodeScreenItem.route) /*TODO: докинуть логики*/
                }
            },
            state = if (activeAuthButton) ButtonState.INITIAL else ButtonState.DISABLED,
            buttonText = R.string.text_continue
        )
    }
}