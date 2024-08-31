package com.example.composeprotject.screen.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprotject.ui.component.button.FilledButton
import com.example.composeprotject.ui.component.spacer.SpacerHeight
import com.example.composeprotject.ui.component.state.FilledButtonState
import com.example.composeprotject.ui.component.utils.CommonDrawables
import com.example.composeprotject.ui.component.utils.CommonString
import com.example.composeprotject.ui.component.utils.eventDetailsDat
import com.example.composeprotject.ui.theme.MeetTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpSuccessScreenPreview() {
    SignUpSuccessScreen(contentPadding = PaddingValues())
}

@Composable
fun SignUpSuccessScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = with(modifier) {
            fillMaxSize()
                .paint(
                    painterResource(id = CommonDrawables.ic_reg_success_fon),
                    contentScale = ContentScale.FillBounds
                )
                .padding(contentPadding)
                .padding(horizontal = MeetTheme.sizes.sizeX16)
        }
    ) {
        Column(
            modifier = Modifier
                .weight(5f)
        ) {
            SpacerHeight(height = MeetTheme.sizes.sizeX20)
            Text(
                text = stringResource(CommonString.text_have_you_made_appointment),
                color = Color.White,
                style = MeetTheme.typography.interSemiBold49
            )
            SpacerHeight(height = MeetTheme.sizes.sizeX12)
            DescriptionBlock(
                eventName = "Супертестировщики",
                startDate = 1725120000,
                shortAddress = "Невский проспект, 11"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionBlock()
        }
        SpacerHeight(height = 28.dp)
    }
}

@Composable
private fun ActionBlock() {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        TextButton(
            onClick = { /*TODO*/ },
            contentPadding = ButtonDefaults.TextButtonContentPadding
        ) {
            Text(
                text = stringResource(id = CommonString.text_my_event),
                color = MeetTheme.colors.primary,
                style = MeetTheme.typography.interMedium18
            )
        }
    }
    SpacerHeight(height = 14.dp)
    FilledButton(state = FilledButtonState.ACTIVE_PRIMARY, buttonText = "Найти другие встречи") {
        //TODO
    }
}

@Composable
private fun DescriptionBlock(
    eventName: String,
    startDate: Long,
    shortAddress: String
) {
    Text(
        text = eventName,
        color = Color.White,
        style = MeetTheme.typography.interMedium20
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX2)
    Text(
        text = eventDetailsDat(
            timestamp = startDate,
            //formatTime = FormatEventDateState.DATE_IN_TIME
        ),
        color = Color.White,
        style = MeetTheme.typography.interMedium20
    )
    SpacerHeight(height = MeetTheme.sizes.sizeX2)
    Text(
        text = shortAddress,
        color = Color.White,
        style = MeetTheme.typography.interMedium20
    )
}

private object NoRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() = Color.Red

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        0.0f,
        0.0f,
        0.0f,
        0.0f
    )
}
