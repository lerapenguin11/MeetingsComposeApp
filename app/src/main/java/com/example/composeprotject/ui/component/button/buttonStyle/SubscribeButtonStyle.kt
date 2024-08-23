package com.example.composeprotject.ui.component.button.buttonStyle

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.state.SubscribeButtonState
import com.example.composeprotject.ui.theme.MeetTheme

class SubscribeButtonStateStyle(private val params: ParamsSubscribeButtonStyle) :
    SubscribeButtonStyle {
    override fun background(state: SubscribeButtonState): Brush {
        return when (state) {
            SubscribeButtonState.SUBSCRIBED_COMMUNITY -> params.doneButtonBackground
            SubscribeButtonState.NOT_SUBSCRIBED_COMMUNITY -> params.defaultButtonBackground
        }
    }

    override fun content(state: SubscribeButtonState): Int {
        return when (state) {
            SubscribeButtonState.SUBSCRIBED_COMMUNITY -> params.doneButtonContent
            SubscribeButtonState.NOT_SUBSCRIBED_COMMUNITY -> params.defaultButtonContent
        }
    }
}

object SubscribeButtonStyleDefault {
    @Composable
    fun style(
        doneButtonBackground: Brush = Brush.horizontalGradient(
            colors = listOf(
                Color(0xff9A10F0), Color(0xff9A10F0)
            )
        ),
        defaultButtonBackground: Brush = MeetTheme.colors.backgroundColorSecondary,
        doneButtonContent: Int = R.drawable.ic_subscribe_bt_done,
        defaultButtonContent: Int = R.drawable.ic_subscribe_bt_default,
    ): SubscribeButtonStateStyle {
        return SubscribeButtonStateStyle(
            params = ParamsSubscribeButtonStyle(
                doneButtonBackground = doneButtonBackground,
                defaultButtonContent = defaultButtonContent,
                doneButtonContent = doneButtonContent,
                defaultButtonBackground = defaultButtonBackground
            )
        )
    }
}

private interface SubscribeButtonStyle {
    fun background(state: SubscribeButtonState): Brush
    fun content(state: SubscribeButtonState): Int
}