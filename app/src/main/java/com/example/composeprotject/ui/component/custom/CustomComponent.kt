package com.example.composeprotject.ui.component.custom

import android.content.Context
import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.AuthViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.i18n.phonenumbers.PhoneNumberUtil

data class CountryData(
    val flagEmoji: String,
    val placeholder: String,
    val callingCode: String
)

fun readCountryDataFromJson(context: Context): Map<String, CountryData> {
    val inputStream = context.resources.openRawResource(R.raw.country_data)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    val type = object : TypeToken<Map<String, CountryData>>() {}.type
    return Gson().fromJson(jsonString, type)
}

var countryData: Map<String, CountryData>? = null

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeInput(
    viewModel: AuthViewModel
) {
    val interactionSource = remember { MutableInteractionSource() }
    var value by remember { mutableStateOf(viewModel.code.value) }
    val v by viewModel.code.collectAsState()

    Box(modifier = Modifier.padding(MeetTheme.sizes.sizeX10)) {
        BasicTextField(
            modifier = Modifier
                .width(240.dp),
            textStyle = TextStyle(
                letterSpacing = 40.sp,
                fontFamily = MeetTheme.typography.heading1.fontFamily,
                textAlign = TextAlign.Start,
                fontSize = 32.sp
            ),
            cursorBrush = SolidColor(Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = v, onValueChange = { if (it.length <= MAX_LENGTH_CODE) viewModel.setCode(it) },
        ) {
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = OutlinedTextFieldDefaults.contentPadding(
                    top = 0.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp,
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    focusedPrefixColor = Color.Transparent,
                    focusedSuffixColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Transparent
                )
            )
        }

        for (i in 0..(3 - v.length)) {
            val offsetLength = INDENTATION_INSIDE_CONTAINER + ((INPUT_WIDTH + 25) * i)

            Box(
                modifier = Modifier
                    .offset(-offsetLength.dp)
                    .width(MeetTheme.sizes.sizeX32)
                    .height(MeetTheme.sizes.sizeX40)
                    .background(Color.White)
                    .align(Alignment.CenterEnd)
            ) {
                Box(
                    modifier = Modifier
                        .size(MeetTheme.sizes.sizeX24)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                        .background(color = MeetTheme.colors.neutralLine)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberInput(
    modifier: Modifier = Modifier
) {
    var callingCodeValue by remember { mutableStateOf(EMPTY_LINE) }
    var numberPhoneValue by remember { mutableStateOf(EMPTY_LINE) }
    var region by remember { mutableStateOf(EMPTY_LINE) }

    if (countryData == null) {
        countryData = readCountryDataFromJson(LocalContext.current)
    }

    val interactionSource = remember { MutableInteractionSource() }
    val colorContent =
        if (callingCodeValue.isNotEmpty()) MeetTheme.colors.neutralActive else MeetTheme.colors.neutralDisabled
    val singleLine = true


    val colors =
        OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MeetTheme.colors.neutralOffWhite,
            unfocusedBorderColor = MeetTheme.colors.neutralOffWhite,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = MeetTheme.colors.neutralOffWhite,
            focusedContainerColor = MeetTheme.colors.neutralOffWhite,
            disabledContainerColor = MeetTheme.colors.neutralOffWhiteDisabled,
            focusedTextColor = MeetTheme.colors.neutralActive,
            unfocusedLeadingIconColor = colorContent,
        )

    Row {
        if (countryData!![region] != null) {
            Row(modifier = Modifier
                .background(MeetTheme.colors.neutralOffWhite)
                .height(36.dp)
                .clip(RoundedCornerShape(4.dp))
                .padding(horizontal = MeetTheme.sizes.sizeX8),
                verticalAlignment = Alignment.CenterVertically) {
                BaseText(
                    text = countryData!![region]?.flagEmoji ?: DEFAULT_FLAG_COUNTRY
                )
                Spacer(
                    modifier = Modifier.width(MeetTheme.sizes.sizeX8)
                )
                BaseText(
                    text = "$PLUS${countryData!![region]?.callingCode}"
                        ?: stringResource(id = R.string.text_default_phone_code),
                    textColor = MeetTheme.colors.neutralActive,
                    textStyle = MeetTheme.typography.bodyText1
                )
            }

            Spacer(
                modifier = Modifier.width(MeetTheme.sizes.sizeX8)
            )
        }

        BasicTextField(
            value = textFieldValueChange(region = region, numberPhone = numberPhoneValue),
            onValueChange = { newValue ->
                numberPhoneValue = newValue

                var countryISO2 = EMPTY_LINE

                if (region.isEmpty()) {
                    countryISO2 = getCountryNameByPhoneCode(numberPhoneValue)
                }
                if (region.isNotEmpty() && newValue.length > 5) {
                    numberPhoneValue = formatPhoneNumber(newValue, region)
                }
                if (countryISO2.isNotEmpty() && countryISO2 != UNSPECIFIED_COUNTRY) {
                    region = countryISO2

                } else if (newValue.isEmpty()) {
                    region = EMPTY_LINE
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
                value = numberPhoneValue,
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

fun textFieldValueChange(region: String, numberPhone: String): String {
    return if (region.isNotEmpty() && region != UNSPECIFIED_COUNTRY) numberPhone.replace(
        "$PLUS${
            countryData!![region]!!.callingCode
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


private const val MAX_LENGTH_CODE = 4
private const val INPUT_WIDTH = 32
private const val INDENTATION_INSIDE_CONTAINER = 15
private const val UNSPECIFIED_COUNTRY = "ZZ"
private const val EMPTY_LINE = ""
private const val DEFAULT_FLAG_COUNTRY = "\uD83C\uDDF7\uD83C\uDDFA"
private const val PLUS = "+"