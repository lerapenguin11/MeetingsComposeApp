package com.example.composeprotject.ui.component.custom

import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.composeprotject.R
import com.example.composeprotject.app.BaseApplication
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.utils.CountryData
import com.example.composeprotject.viewModel.AuthViewModel
import com.google.i18n.phonenumbers.PhoneNumberUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberInput(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val countryData = (context.applicationContext as BaseApplication).countryData

    var phoneNumberValue by remember { mutableStateOf(EMPTY_LINE) }
    var region by remember { mutableStateOf(EMPTY_LINE) }

    val interactionSource = remember { MutableInteractionSource() }
    val singleLine = true

    val colors =
        OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MeetTheme.colors.neutralOffWhite,
            unfocusedBorderColor = MeetTheme.colors.neutralOffWhite,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = MeetTheme.colors.neutralOffWhite,
            focusedContainerColor = MeetTheme.colors.neutralOffWhite,
            disabledContainerColor = MeetTheme.colors.neutralOffWhiteDisabled,
            focusedTextColor = MeetTheme.colors.neutralActive
        )

    Row {
        if (countryData[region] != null) {
            Row(
                modifier = Modifier
                    .background(MeetTheme.colors.neutralOffWhite)
                    .height(36.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(horizontal = MeetTheme.sizes.sizeX8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseText(
                    text = countryData[region]?.flagEmoji ?: DEFAULT_FLAG_COUNTRY
                )
                Spacer(
                    modifier = modifier.width(MeetTheme.sizes.sizeX8)
                )
                BaseText(
                    text = "$PLUS${countryData[region]?.callingCode}",
                    textColor = MeetTheme.colors.neutralActive,
                    textStyle = MeetTheme.typography.bodyText1
                )
            }

            Spacer(
                modifier = modifier.width(MeetTheme.sizes.sizeX8)
            )
        }

        BasicTextField(
            value = textFieldValueChange(
                region = region,
                numberPhone = phoneNumberValue,
                countryData
            ),
            onValueChange = { newValue ->
                phoneNumberValue = newValue

                var countryISO2 = EMPTY_LINE
                var cleanPhoneNumber = cleanPhoneNumber(newValue)
                var cleanPlaceholder =
                    cleanPhoneNumber(countryData[region]?.placeholder ?: EMPTY_LINE)

                if (region.isEmpty()) {
                    countryISO2 = getCountryNameByPhoneCode(phoneNumberValue)
                }
                if (region.isNotEmpty() && newValue.length > 5) {
                    phoneNumberValue = formatPhoneNumber(newValue, region)
                }
                if (countryISO2.isNotEmpty() && countryISO2 != UNSPECIFIED_COUNTRY) {
                    region = countryISO2

                } else if (newValue.isEmpty()) {
                    region = EMPTY_LINE
                }

                if (cleanPhoneNumber.length == cleanPlaceholder.length) {
                    authViewModel.activeAuthButton(isEnabled = true)
                    val isValidation = isValidNumber(
                        phoneNumber = cleanPhoneNumber,
                        countryCode = countryData[region]?.callingCode ?: EMPTY_LINE,
                        country = region
                    )
                    authViewModel.validationPhoneNumber(isValidation = isValidation)
                } else {
                    authViewModel.activeAuthButton(isEnabled = false)
                }
            },
            modifier = modifier
                .border(0.dp, Color.Transparent)
                .height(36.dp)
                .fillMaxWidth(),
            interactionSource = interactionSource,
            enabled = true,
            singleLine = singleLine,
            textStyle = MeetTheme.typography.bodyText1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        ) {
            OutlinedTextFieldDefaults.DecorationBox(
                value = phoneNumberValue,
                visualTransformation = VisualTransformation.None,
                innerTextField = it,
                singleLine = singleLine,
                enabled = true,
                placeholder = {
                    Text(
                        text = formatPlaceholderPhoneNumber(region = region),
                        style = MeetTheme.typography.bodyText1,
                        color = MeetTheme.colors.neutralDisabled
                    )
                },
                interactionSource = interactionSource,
                contentPadding =
                OutlinedTextFieldDefaults.contentPadding(
                    top = MeetTheme.sizes.sizeX6,
                    bottom = MeetTheme.sizes.sizeX6,
                    start = MeetTheme.sizes.sizeX8,
                    end = MeetTheme.sizes.sizeX8
                ),
                colors = colors,
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = true,
                        isError = false,
                        colors = colors,
                        interactionSource = interactionSource,
                        shape = RoundedCornerShape(4.dp),
                        focusedBorderThickness = 2.dp
                    )
                }
            )
        }
    }
}

fun textFieldValueChange(
    region: String,
    numberPhone: String,
    countryData: Map<String, CountryData>
): String {
    return if (region.isNotEmpty() && region != UNSPECIFIED_COUNTRY) numberPhone.replace(
        "$PLUS${
            countryData[region]!!.callingCode
        }", EMPTY_LINE
    ) else numberPhone
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
    val isValidNumber = phoneNumberUtil.parse("$countryCode$phoneNumber", country)

    return try {
        phoneNumberUtil.isValidNumber(isValidNumber)
    } catch (e: Exception) {
        false
    }
}

fun formatPhoneNumber(value: String, pattern: String): String {
    return if (pattern.isEmpty()) {
        return value
    } else {
        PhoneNumberUtils.formatNumber(value, pattern)
    }
}

@Composable
fun formatPlaceholderPhoneNumber(region: String): String {
    return if (region.isEmpty()) {
        stringResource(id = R.string.text_ph_phone_number_with_code)
    } else {
        stringResource(id = R.string.text_ph_phone_number)
    }
}

fun cleanPhoneNumber(phoneNumber: String): String {
    return phoneNumber.replace(WHITESPACE, EMPTY_LINE).replace(DASH, EMPTY_LINE)
}

private const val UNSPECIFIED_COUNTRY = "ZZ"
private const val EMPTY_LINE = ""
private const val DEFAULT_FLAG_COUNTRY = "\uD83C\uDDF7\uD83C\uDDFA"
private const val PLUS = "+"
private const val WHITESPACE = " "
private const val DASH = "-"