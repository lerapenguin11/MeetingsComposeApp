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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.example.composeprotject.ui.component.utils.NoRippleTheme
import com.example.composeprotject.ui.component.utils.eventDate
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.SingUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration.Companion.seconds

private val signUpSteps by lazy { RegistrationScreenState.entries.toTypedArray() }

@Composable
fun SignUpScreen(
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
    val phoneNumber by singUpViewModel.getPhoneNumber().collectAsStateWithLifecycle()
    val userParam by singUpViewModel.getUserParam().collectAsStateWithLifecycle()
    val buttonState by singUpViewModel.getButtonState().collectAsStateWithLifecycle()
    val test by singUpViewModel.getIsLoading().collectAsStateWithLifecycle()

    if (inputValue.isEmpty()) {
        singUpViewModel.updateButtonState(state = FilledButtonState.DISABLED)
    }

    LaunchedEffect(test) {
        if (test) {
            singUpViewModel.updateButtonState(state = FilledButtonState.DISABLED)
            val nextStepIndex = signUpSteps.indexOf(currentStep) + STEP
            currentStep = signUpSteps[nextStepIndex]
        }
    }

    val coroutineScope = rememberCoroutineScope() //TODO удалить

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
            SignTopBar(onCancel = { onCancelScreen() })
            SpacerHeight(height = MeetTheme.sizes.sizeX12)
            DescriptionBlock(
                eventName = title,
                startDate = startDate,
                shortAddress = shortAddress
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX24)
            InputBlock(
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
                        singUpViewModel = singUpViewModel,
                        coroutineScope = coroutineScope,
                        onSecondsRemaining = {
                            secondsRemaining = it
                        }
                    )
                    goToNextStepRegistration(
                        nextStepExists = nextStepExists,
                        onCurrentStep = { currentStep = it },
                        nextStepIndex = nextStepIndex,
                        currentStep = currentStep,
                        onInputValue = { inputValue = it }
                    )
                },
                state = buttonState,
            )
            SpacerHeight(height = 28.dp)
        }
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
        onCurrentStep(signUpSteps[nextStepIndex])
        onInputValue(EMPTY_LINE)
    }
}

private fun sendUserDataAndStartTimer(
    currentStep: RegistrationScreenState,
    singUpViewModel: SingUpViewModel,
    coroutineScope: CoroutineScope,
    onSecondsRemaining: (Int) -> Unit
) {
    if (RegistrationScreenState.INPUT_NUMBER_PHONE == currentStep) {
        singUpViewModel.updateButtonState(state = FilledButtonState.LOADING)
        coroutineScope.launch {
            singUpViewModel.test()
        }
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
            append(stringResource(R.string.text_get_new_code_via))
            append(" $secondsRemaining")
        } else {
            append(stringResource(R.string.text_get_new_code))
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
private fun ActionComponent(
    screenState: RegistrationScreenState,
    buttonState: FilledButtonState,
    onClick: (FilledButtonState) -> Unit
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
        state = buttonState,
        buttonText = getActionButtonText(screenState = screenState)
    ) {
        onClick(buttonState)
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
            stringResource(R.string.text_send_and_confirm_an_entry)
        }

        RegistrationScreenState.INPUT_NUMBER_PHONE -> {
            stringResource(R.string.text_get_code)
        }
    }
}

@Composable
private fun InputBlock(
    screenState: RegistrationScreenState,
    isEnabled: Boolean,
    onInputName: (String) -> Unit,
    onInputCode: (String) -> Unit,
    onInputNumberPhone: (String) -> Unit,
    onValidationPhoneNumber: (Boolean) -> Unit,
    phoneNumber: String
) {
    when (screenState) {
        RegistrationScreenState.INPUT_NAME -> {
            SimpleInputField(
                textPlaceholder = stringResource(R.string.text_name_and_surname),
                isEnabled = isEnabled,
                state = InputState.SUCCESS,
                onValueChange = { newValue ->
                    onInputName(newValue)
                }
            )
        }

        RegistrationScreenState.INPUT_CODE -> {
            SimpleInputField(
                textPlaceholder = stringResource(R.string.text_placeholder_code),
                isEnabled = isEnabled,
                state = InputState.SUCCESS,
                onValueChange = { newValue ->
                    onInputCode(newValue)
                }
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX8)
            Text(
                text = "Отправили код на ${phoneNumber}",
                color = MeetTheme.colors.darkGray,
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
            text = stringResource(R.string.text_log_in_and_make_appointment),
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