package com.example.composeprotject.ui.component.custom

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeprotject.R
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.SplashScreenViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.i18n.phonenumbers.PhoneNumberUtil

//TODO: Доделать ввод номера

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

@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (value: String) -> Unit
) {
    if (countryData == null) {
        countryData = readCountryDataFromJson(LocalContext.current)
    }

    // TODO: move to enum
    val currentCountryISO2 = remember { mutableStateOf<String?>("RU") }
    val currentCountry = countryData!![currentCountryISO2.value]
    val currentCountryCode =
        if (currentCountryISO2.value != null) countryData!![currentCountryISO2.value!!]!!.callingCode else null

    val nationalNumber = if (currentCountry != null)
        value.replace("+${currentCountry.callingCode}", "") else value

    val onTextFieldValueChange = fun (value: String) {
        if (currentCountryCode != null) {
            onValueChange("+${currentCountryCode}${value.replace(currentCountryCode, "")}")
            return;
        }

        val parseResult = PhoneNumberUtil.getInstance().parse(value, "RU")

    }

//    val nationalNumber = currentCountryISO2 != null ?

    Row(modifier = Modifier.background(Color.White)) {
        if (currentCountry != null) {
            Row {
                Text(text = currentCountry.flagEmoji)
                Text(text = "+${currentCountry.callingCode}")
            }
        }

        BasicTextField(
            modifier = Modifier
                .border(0.dp, Color.Transparent),
            value = nationalNumber, onValueChange = onTextFieldValueChange,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeInput(
    viewModel: SplashScreenViewModel
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
            value = v, onValueChange = { if (it.length <= MAX_LENGTH_CODE)  viewModel.setCode(it) },
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
                    .width(32.dp)
                    .height(40.dp)
                    .background(Color.White)
                    .align(Alignment.CenterEnd)
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                        .background(color = MeetTheme.colors.neutralLine)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test(){
    PhoneNumberInput(
        value = "",
        onValueChange = {}
    )
}

private const val MAX_LENGTH_CODE = 4
private const val INPUT_WIDTH = 32
private const val INDENTATION_INSIDE_CONTAINER = 15