package com.example.composeprotject.ui.component.phoneInput

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.app.BaseApplication
import com.example.composeprotject.ui.component.input.inputState.InputColors
import com.example.composeprotject.ui.component.input.inputState.InputColorsDefaults
import com.example.composeprotject.ui.component.state.InputState
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.utils.CountryData
import com.google.i18n.phonenumbers.PhoneNumberUtil

@Composable
fun PhoneNumberContainer(
    onValueChange: (String) -> Unit,
    onValidityChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val countryData = (context.applicationContext as? BaseApplication)?.countryData
    var phoneNumberValue by remember { mutableStateOf(EMPTY_LINE) }
    var region by remember { mutableStateOf(EMPTY_LINE) }
    val countryDataRegion = countryData?.get(region)
    val mask = countryDataRegion?.placeholder
        ?: stringResource(id = CommonString.text_ph_phone_number)
    val colorBorder =
        if (phoneNumberValue.isNotEmpty()) MeetTheme.colors.secondary else MeetTheme.colors.primary

    LaunchedEffect(phoneNumberValue) {
        onValueChange("$PLUS${countryDataRegion?.callingCode.orEmpty()}$phoneNumberValue")

        if (region.isNotEmpty() && countryDataRegion != null) {
            onValidityChange(isValidNumber(phoneNumberValue, region, countryDataRegion.callingCode))
        }
    }

    Row {
        countryDataRegion?.let { country ->
            CallingCode(
                country = country
            )
        }
        PhoneNumber(
            colorBorder = colorBorder,
            mask = mask,
            maskNumber = MASK_NUMBER,
            countryDataRegion = countryDataRegion,
            region = region,
            state = InputState.SUCCESS,
            value = textFieldValueChange(
                region = region,
                numberPhone = phoneNumberValue,
                callingCode = countryDataRegion?.callingCode.orEmpty()
            ),
            onValueChange = { newValue ->
                phoneNumberValue = newValue

                var countryISO2 = EMPTY_LINE
                if (region.isEmpty()) {
                    countryISO2 = getCountryNameByPhoneCode(phoneNumberValue)
                }
                if (countryISO2.isNotEmpty() && countryISO2 != UNSPECIFIED_COUNTRY) {
                    region = countryISO2

                } else if (newValue.isEmpty()) {
                    region = EMPTY_LINE
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumber(
    region: String,
    value: String,
    state: InputState,
    mask: String,
    maskNumber: Char,
    modifier: Modifier = Modifier,
    inputColors: InputColors = InputColorsDefaults.colors(),
    onValueChange: (String) -> Unit,
    countryDataRegion: CountryData?,
    colorBorder: Color
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val singleLine = true

    val colors =
        OutlinedTextFieldDefaults.colors(
            errorContainerColor = inputColors.background(state),
            errorBorderColor = inputColors.background(state),
            focusedBorderColor = colorBorder,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = MeetTheme.colors.secondary,
            focusedContainerColor = MeetTheme.colors.secondary,
            focusedTextColor = MeetTheme.colors.black,
            unfocusedTextColor = MeetTheme.colors.black,
            errorTextColor = MeetTheme.colors.black
        )

    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { it ->
            onValueChange(it.take(mask.count { it == maskNumber }))
        },
        interactionSource = interactionSource,
        enabled = true,
        singleLine = singleLine,
        textStyle = MeetTheme.typography.interRegular19,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        visualTransformation = PhoneVisualTransformation(
            maskNumber = maskNumber,
            mask = mask
        )
    ) {
        TextFieldDefaults.DecorationBox(
            value = value,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = singleLine,
            enabled = true,
            placeholder = {
                Text(
                    text = formatPlaceholderPhoneNumber(region = region, countryDataRegion),
                    style = MeetTheme.typography.interRegular19,
                    color = MeetTheme.colors.neutralDisabled
                )
            },
            interactionSource = interactionSource,
            contentPadding =
            OutlinedTextFieldDefaults.contentPadding(
                top = MeetTheme.sizes.sizeX16,
                bottom = MeetTheme.sizes.sizeX16,
                start = MeetTheme.sizes.sizeX20,
                end = MeetTheme.sizes.sizeX20
            ),
            colors = colors,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = true,
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

@Composable
fun CallingCode(
    country: CountryData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MeetTheme.colors.secondary)
            .height(56.dp)
            .padding(
                horizontal = MeetTheme.sizes.sizeX20,
                vertical = MeetTheme.sizes.sizeX16
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseText(
            text = country.flagEmoji
        )
        Spacer(
            modifier = Modifier.width(MeetTheme.sizes.sizeX8)
        )
        BaseText(
            text = "$PLUS${country.callingCode}",
            textColor = MeetTheme.colors.black,
            textStyle = MeetTheme.typography.interMedium18
        )
    }
    Spacer(
        modifier = Modifier.width(MeetTheme.sizes.sizeX8)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimplePhoneInputFields(
    textPlaceholder: String,
    isEnabled: Boolean,
    state: InputState,
    inputText: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    inputColors: InputColors = InputColorsDefaults.colors(),
    onValueChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var textOverflow by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

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
            val value = newValue.take(
                MASK.count { it == MASK_NUMBER })
            onValueChange(value)
        },
        onTextLayout = { textLayoutResult ->
            textOverflow = textLayoutResult.hasVisualOverflow
        },
        modifier = modifier
            .fillMaxWidth(),
        interactionSource = interactionSource,
        enabled = isEnabled,
        singleLine = singleLine,
        textStyle = MeetTheme.typography.interRegular19,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        visualTransformation = PhoneVisualTransformation(
            maskNumber = MASK_NUMBER,
            mask = MASK
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
                bottom = MeetTheme.sizes.sizeX16,
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

fun textFieldValueChange(
    region: String,
    numberPhone: String,
    callingCode: String
): String {
    return if (region.isNotEmpty() && region != UNSPECIFIED_COUNTRY) {
        numberPhone.replace(
            "${PLUS}${
                callingCode
            }", EMPTY_LINE
        )
    } else numberPhone
}

fun getCountryNameByPhoneCode(phoneCode: String): String {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()

    return try {
        if (phoneCode.isNotEmpty()) {
            val countryName = phoneNumberUtil.getRegionCodeForCountryCode(phoneCode.toInt())
            return countryName
        } else {
            EMPTY_LINE
        }
    } catch (e: Exception) {
        EMPTY_LINE
    }
}

fun isValidNumber(phoneNumber: String, country: String, countryCode: String): Boolean {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()

    return try {
        val isValidNumber = phoneNumberUtil.parse("$countryCode$phoneNumber", country)
        phoneNumberUtil.isValidNumber(isValidNumber)
    } catch (e: Exception) {
        false
    }
}

@Composable
fun formatPlaceholderPhoneNumber(region: String, countryDataRegion: CountryData?): String {
    return if (region.isEmpty()) {
        stringResource(id = R.string.text_ph_phone_number_with_code)
    } else {
        countryDataRegion?.placeholder ?: stringResource(id = R.string.text_ph_phone_number)
    }
}

fun cleanPhoneNumber(phoneNumber: String): String {
    return phoneNumber.replace(
        WHITESPACE,
        EMPTY_LINE
    ).replace(
        DASH,
        EMPTY_LINE
    )
}

fun getPhoneNumber(callingCode: String, phone: String): String {
    return "${PLUS}$callingCode $phone"
}

private const val UNSPECIFIED_COUNTRY = "ZZ"
private const val MASK_NUMBER = 'X'
private const val EMPTY_LINE = ""
private const val MASK = "+X XXX-XXX-XX-XX"
private const val UNSPECIFIED_CALLING_CODE = "001"
private const val PLUS = "+"
private const val WHITESPACE = " "
private const val DASH = "-"
private const val MIN_PHONE_NUMBER_LENGTH = 5