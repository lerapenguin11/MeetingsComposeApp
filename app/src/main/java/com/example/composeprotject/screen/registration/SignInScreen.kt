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
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.result.SendCodeStatus
import com.example.composeprotject.screen.state.RegistrationScreenState
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.input.SimpleInputField
import com.example.composeprotject.ui.component.phoneInput.PhoneNumberContainer
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.component.utils.eventDate
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.SingUpViewModel
import com.example.domain.model.signUp.UserParam
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration.Companion.seconds

private val signUpSteps by lazy { RegistrationScreenState.entries.toTypedArray() }

@Composable
fun SignInScreen(
    title: String,
    eventId: Int,
    startDate: Long,
    shortAddress: String,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    singUpViewModel: SingUpViewModel = koinViewModel(),
    onCancelScreen: () -> Unit
) {
    var currentStep by remember { mutableStateOf(RegistrationScreenState.INPUT_NAME) }
    var inputValue by remember { mutableStateOf(EMPTY_LINE) }
    val code by singUpViewModel.getCodeFlow().collectAsStateWithLifecycle()
    val phoneNumber by singUpViewModel.getPhoneNumberFlow().collectAsStateWithLifecycle()
    val userParam by singUpViewModel.getUserParamFlow().collectAsStateWithLifecycle()
    val buttonState by singUpViewModel.getButtonStateFlow().collectAsStateWithLifecycle()
    val isSendPhoneVerificationCode by singUpViewModel.getIsSendPhoneVerificationCodeFlow()
        .collectAsStateWithLifecycle()
    val authToken by singUpViewModel.getTokenFlow().collectAsStateWithLifecycle()

    if (inputValue.isEmpty()) {
        singUpViewModel.updateButtonState(state = FilledButtonState.DISABLED)
    }

    LaunchedEffect(authToken?.token) {
        if (authToken?.token != null) {
            authToken!!.token?.let { singUpViewModel.saveAuthToken(token = it) }
            //TODO: переход на SignInSuccuessScreen
        }
    }

    LaunchedEffect(isSendPhoneVerificationCode) {
        if (!isSendPhoneVerificationCode) {
            singUpViewModel.updateButtonState(state = FilledButtonState.DISABLED)
            val nextStepIndex = signUpSteps.indexOf(currentStep) + STEP
            currentStep = signUpSteps[nextStepIndex]
        }
    }

    var secondsRemaining by remember { mutableIntStateOf(FINISH_TIMER) }
    LaunchedEffect(key1 = secondsRemaining) {
        if (secondsRemaining > FINISH_TIMER) {
            while (true) {
                delay(SECOND.seconds)
                secondsRemaining--
            }
        }
    }

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
            SignTopBar(
                onCancel = { onCancelScreen() }
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX12)
            DescriptionBlock(
                eventName = title,
                startDate = startDate,
                shortAddress = shortAddress
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX24)
            InputBlock(
                inputValue = inputValue,
                codeState = authToken?.success,
                screenState = currentStep,
                isEnabled = true,
                onInputName = {
                    inputValue = it
                    singUpViewModel.updateUserName(name = inputValue)
                    updateSignupButtonState(
                        inputValue = inputValue,
                        singUpViewModel = singUpViewModel
                    )
                },
                onInputCode = {
                    inputValue = it
                    singUpViewModel.updateConfirmationCode(code = inputValue)
                    updateSignupButtonState(
                        inputValue = inputValue,
                        singUpViewModel = singUpViewModel
                    )
                },
                onInputNumberPhone = {
                    inputValue = it
                    singUpViewModel.updatePhoneNumber(phoneNumber = inputValue)
                },
                phoneNumber = "+${phoneNumber.orEmpty()}",
                onValidationPhoneNumber = {
                    singUpViewModel.updateButtonState(
                        state = if (it) {
                            FilledButtonState.ACTIVE_PRIMARY
                        } else {
                            FilledButtonState.DISABLED
                        }
                    )
                }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextGetNewCode(secondsRemaining = secondsRemaining, currentStep = currentStep) {
                secondsRemaining = START_TIMER
            }
            FilledButton(
                buttonText = getActionButtonText(screenState = currentStep),
                onClick = {
                    val nextStepIndex = signUpSteps.indexOf(currentStep) + STEP
                    val nextStepExists = nextStepIndex < signUpSteps.size
                    sendUserDataAndStartTimer(
                        currentStep = currentStep,
                        signUpViewModel = singUpViewModel,
                        userParam = UserParam(
                            eventId = eventId,
                            name = userParam.first.orEmpty(),
                            phoneNumber = userParam.second.orEmpty(),
                            userInterests = null
                        ),
                        onSecondsRemaining = {
                            secondsRemaining = it
                        },
                        onInputValue = { inputValue = it }
                    )
                    goToNextStepRegistration(
                        nextStepExists = nextStepExists,
                        onCurrentStep = { currentStep = it },
                        nextStepIndex = nextStepIndex,
                        currentStep = currentStep,
                        onInputValue = { inputValue = it }
                    )
                    code?.let {
                        phoneNumber?.let { it1 ->
                            sendConfirmationCode(
                                currentStep = currentStep,
                                singUpViewModel = singUpViewModel,
                                code = it,
                                phoneNumber = it1
                            )
                        }
                    }
                },
                state = buttonState,
            )
            SpacerHeight(height = 28.dp)
        }
    }
}

private fun sendConfirmationCode(
    currentStep: RegistrationScreenState,
    singUpViewModel: SingUpViewModel,
    code: String,
    phoneNumber: String
) {
    if (RegistrationScreenState.INPUT_CODE == currentStep) {
        singUpViewModel.sendConfirmationCode(code = code, phoneNumber = phoneNumber)
    }
}

private fun goToNextStepRegistration(
    nextStepExists: Boolean,
    nextStepIndex: Int,
    currentStep: RegistrationScreenState,
    onCurrentStep: (RegistrationScreenState) -> Unit,
    onInputValue: (String) -> Unit
) {
    if (nextStepExists && RegistrationScreenState.INPUT_NUMBER_PHONE != currentStep) {
        onInputValue(EMPTY_LINE)
        onCurrentStep(signUpSteps[nextStepIndex])
    }
}

private fun sendUserDataAndStartTimer(
    currentStep: RegistrationScreenState,
    signUpViewModel: SingUpViewModel,
    userParam: UserParam,
    onSecondsRemaining: (Int) -> Unit,
    onInputValue: (String) -> Unit
) {
    if (RegistrationScreenState.INPUT_NUMBER_PHONE == currentStep) {
        signUpViewModel.updateButtonState(state = FilledButtonState.LOADING)
        signUpViewModel.sendPhoneVerificationCode(userParam = userParam)
        onInputValue(EMPTY_LINE)
        onSecondsRemaining(START_TIMER)
    }
}

private fun updateSignupButtonState(inputValue: String, singUpViewModel: SingUpViewModel) {
    if (inputValue.isEmpty()) {
        singUpViewModel.updateButtonState(state = FilledButtonState.DISABLED)
    } else {
        singUpViewModel.updateButtonState(state = FilledButtonState.ACTIVE_PRIMARY)
    }
}

@Composable
private fun TextGetNewCode(
    secondsRemaining: Int,
    currentStep: RegistrationScreenState,
    modifier: Modifier = Modifier,
    onGetNewCode: () -> Unit
) {
    val isCountdownActive = secondsRemaining > FINISH_TIMER
    val text = buildAnnotatedString {
        if (isCountdownActive) {
            append(stringResource(CommonString.text_get_new_code_via))
            append(" $secondsRemaining")
        } else {
            append(stringResource(CommonString.text_get_new_code))
        }
    }
    if (currentStep == RegistrationScreenState.INPUT_CODE) {
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
            Text(
                modifier = modifier
                    .then(
                        if (!isCountdownActive) {
                            modifier.clickable {
                                onGetNewCode()
                            }
                        } else modifier
                    ),
                text = text,
                color = if (isCountdownActive) MeetTheme.colors.darkGray else MeetTheme.colors.primary,
                style = MeetTheme.typography.interMedium18
            )
        }
        SpacerHeight(height = MeetTheme.sizes.sizeX24)
    }
}

@Composable
private fun getActionButtonText(
    screenState: RegistrationScreenState
): String {
    return when (screenState) {
        RegistrationScreenState.INPUT_NAME -> {
            stringResource(id = CommonString.text_continue)
        }

        RegistrationScreenState.INPUT_CODE -> {
            stringResource(CommonString.text_send_and_confirm_an_entry)
        }

        RegistrationScreenState.INPUT_NUMBER_PHONE -> {
            stringResource(CommonString.text_get_code)
        }
    }
}

@Composable
private fun InputBlock(
    screenState: RegistrationScreenState,
    isEnabled: Boolean,
    phoneNumber: String,
    codeState: SendCodeStatus?,
    onInputName: (String) -> Unit,
    onInputCode: (String) -> Unit,
    onInputNumberPhone: (String) -> Unit,
    onValidationPhoneNumber: (Boolean) -> Unit,
    inputValue: String
) {
    when (screenState) {
        RegistrationScreenState.INPUT_NAME -> {
            SimpleInputField(
                inputText = inputValue, //TODO
                textPlaceholder = stringResource(CommonString.text_name_and_surname),
                isEnabled = isEnabled,
                state = InputState.SUCCESS,
                onValueChange = { newValue ->
                    onInputName(newValue)
                }
            )
        }

        RegistrationScreenState.INPUT_CODE -> {
            SimpleInputField(
                inputText = inputValue, //TODO
                textPlaceholder = stringResource(CommonString.text_placeholder_code),
                isEnabled = isEnabled,
                state = when (codeState) {
                    SendCodeStatus.SUCCESS -> InputState.SUCCESS
                    SendCodeStatus.FAILURE, SendCodeStatus.ERROR -> InputState.ERROR
                    null -> InputState.SUCCESS
                },
                limit = LENGTH_CODE,
                keyboardType = KeyboardType.NumberPassword,
                onValueChange = { newValue ->
                    onInputCode(newValue)
                }
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX8)
            val text = buildAnnotatedString {
                append(stringResource(CommonString.text_sent_code))
                append(" $phoneNumber")
            }
            Text(
                text = when (codeState) {
                    SendCodeStatus.SUCCESS -> text.text
                    SendCodeStatus.FAILURE -> stringResource(CommonString.text_incorrect_code)
                    SendCodeStatus.ERROR -> stringResource(CommonString.text_something_went_wrong)
                    null -> text.text
                },
                color = if (codeState == SendCodeStatus.SUCCESS || codeState == null) MeetTheme.colors.darkGray else MeetTheme.colors.error,
                style = MeetTheme.typography.interMedium14
            )
        }

        RegistrationScreenState.INPUT_NUMBER_PHONE -> {
            PhoneNumberContainer(
                onValueChange = { newValue ->
                    onInputNumberPhone(newValue.replace(PLUS, EMPTY_LINE))
                },
                onValidityChange = {
                    onValidationPhoneNumber(it)
                }
            )
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
private fun SignTopBar(
    onCancel: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .weight(5f),
            text = stringResource(CommonString.text_log_in_and_make_appointment),
            color = Color.Black,
            style = MeetTheme.typography.interSemiBold49
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = MeetTheme.sizes.sizeX10))
                .clickable {
                    onCancel()
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

private const val SECOND = 1
private const val FINISH_TIMER = 0
private const val START_TIMER = 60
private const val STEP = 1
private const val EMPTY_LINE = ""
private const val PLUS = "+"
private const val LENGTH_CODE = 4