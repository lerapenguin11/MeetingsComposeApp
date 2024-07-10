package com.example.composeprotject.ui.component.custom

import android.telephony.PhoneNumberUtils
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme
import com.example.composeprotject.viewModel.SplashScreenViewModel
import java.sql.Array


data class CountryData(
    val flagEmoji: String,
    val placeholder: String,
    val callingCode: String
)

val mockCountryData = mapOf<String, CountryData>(
    "RU" to CountryData(
        flagEmoji = "\uD83C\uDDF7\uD83C\uDDFA",
        callingCode = "7",
        placeholder = "XXX-XXX-XX-XX"
    ),
    "US" to CountryData(
        flagEmoji = "\uD83C\uDDFA\uD83C\uDDF8",
        callingCode = "1",
        placeholder = "XXX-XXX-XXXX"
    )
)


@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (value: String) -> Unit
) {
    // TODO: move to enum
    val currentCountryISO2 by remember { mutableStateOf<String?>("RU") }
    val currentCountry = mockCountryData[currentCountryISO2]

    val nationalNumber = if (currentCountry != null)
        value.replace("+${currentCountry!!.callingCode}", "") else value


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
            value = nationalNumber, onValueChange = onValueChange
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

    Box(modifier = Modifier.padding(10.dp)) {
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
            value = v, onValueChange = { if (it.length <= 4)  viewModel.setCode(it) },
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
            val offsetLength = 15 + ((32 + 25) * i)

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