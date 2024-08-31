package com.example.composeprotject.ui.component_old.custom

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
import com.example.composeprotject.viewModel_old.auth.AuthPhoneNumberViewModel
import com.google.i18n.phonenumbers.PhoneNumberUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberInputOld(
    modifier: Modifier = Modifier,
    authPhoneNumberViewModel: AuthPhoneNumberViewModel,
    onValueChange: (String) -> Unit
) {
    val context = LocalContext.current
    val countryData = (context.applicationContext as? BaseApplication)?.countryData
    var phoneNumberValue by remember { mutableStateOf(EMPTY_LINE) }
    var region by remember { mutableStateOf(EMPTY_LINE) }
    val countryDataRegion = countryData?.get(region)
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
        countryDataRegion?.let { country ->
            Row(
                modifier = Modifier
                    .background(MeetTheme.colors.neutralOffWhite)
                    .height(36.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(horizontal = MeetTheme.sizes.sizeX8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseText(
                    text = country.flagEmoji
                )
                Spacer(
                    modifier = modifier.width(MeetTheme.sizes.sizeX8)
                )
                BaseText(
                    text = "$PLUS${country.callingCode}",
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
                callingCode = countryDataRegion?.callingCode.orEmpty()
            ),
            onValueChange = { newValue ->
                phoneNumberValue = newValue
                onValueChange(newValue)

                var countryISO2 = EMPTY_LINE
                val cleanPhoneNumber = cleanPhoneNumber(newValue)
                val cleanPlaceholder =
                    cleanPhoneNumber(countryDataRegion?.placeholder.orEmpty())

                if (region.isEmpty()) {
                    countryISO2 = getCountryNameByPhoneCode(phoneNumberValue)
                }
                if (region.isNotEmpty() && newValue.length > MIN_PHONE_NUMBER_LENGTH) {
                    phoneNumberValue = formatPhoneNumber(newValue, region)
                }
                if (countryISO2.isNotEmpty() && countryISO2 != UNSPECIFIED_COUNTRY) {
                    region = countryISO2

                } else if (newValue.isEmpty()) {
                    region = EMPTY_LINE
                }

                if (cleanPhoneNumber.length == cleanPlaceholder.length && region.isNotEmpty()) {
                    authPhoneNumberViewModel.activeAuthButton(isEnabled = true)
                    val isValidation = isValidNumber(
                        phoneNumber = cleanPhoneNumber,
                        countryCode = countryDataRegion?.callingCode.orEmpty(),
                        country = region
                    )
                    authPhoneNumberViewModel.validationPhoneNumber(isValidation = isValidation)
                    authPhoneNumberViewModel.phoneNumber(getPhoneNumber(
                        callingCode = countryDataRegion?.callingCode.orEmpty(),
                        phone = phoneNumberValue
                    ))
                } else {
                    authPhoneNumberViewModel.activeAuthButton(isEnabled = false)
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
                        text = formatPlaceholderPhoneNumber(
                            region = region,
                            countryDataRegion = countryDataRegion
                        ),
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
    callingCode: String
): String {
    return if (region.isNotEmpty() && region != UNSPECIFIED_COUNTRY) {
        numberPhone.replace(
            "$PLUS${
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

fun formatPhoneNumber(value: String, pattern: String): String {
    return if (pattern.isEmpty() || UNSPECIFIED_CALLING_CODE == pattern) {
        return value
    } else {
        PhoneNumberUtils.formatNumber(value, pattern)
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
    return phoneNumber.replace(WHITESPACE, EMPTY_LINE).replace(DASH, EMPTY_LINE)
}

fun getPhoneNumber(callingCode: String, phone : String) : String{
    return  "$PLUS$callingCode $phone"
}

private const val UNSPECIFIED_COUNTRY = "ZZ"
private const val EMPTY_LINE = ""
private const val UNSPECIFIED_CALLING_CODE = "001"
private const val PLUS = "+"
private const val WHITESPACE = " "
private const val DASH = "-"
private const val MIN_PHONE_NUMBER_LENGTH = 5