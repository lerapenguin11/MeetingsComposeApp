package com.example.composeprotject.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MeetColorScheme(
    val disabledButton : Color,
    val brandDark : Color,
    val brandDarkInnerShadow : Color,
    val brandDefault : Color,
    val brandDarkMode : Color,
    val brandLight : Color,
    val brandBG : Color,
    val neutralActive : Color,
    val neutralDark : Color,
    val neutralBody : Color,
    val neutralWeak : Color,
    val neutralDisabled : Color,
    val neutralDisabled2 : Color,
    val neutralLine : Color,
    val neutralOffWhite : Color,
    val neutralOffWhiteDisabled : Color,
    val neutralWhite : Color,
    val accentDanger : Color,
    val accentWarning : Color,
    val accentSuccess : Color,
    val accentSafe : Color,
    val blueColor : Color,
    val purpleColor : Color,
    val primaryDefault : Color,
    val tabUnselectedContentColor : Color

)

val LightColorScheme = MeetColorScheme(
    disabledButton = Color(0xffCB9FFD),
    brandDark = Color(0xff660EC8),
    brandDarkInnerShadow = Color(0xff530BA2),
    brandDefault = Color(0xff9A41FE),
    brandDarkMode = Color(0xff8207E8),
    brandLight = Color(0xffECDAFF),
    brandBG = Color(0xffF5ECFF),
    neutralActive = Color(0xff29183B),
    neutralDark = Color(0xff190E26),
    neutralBody = Color(0xff1D0835),
    neutralWeak = Color(0xffA4A4A4),
    neutralDisabled = Color(0xffADB5BD),
    neutralDisabled2 = Color(0xffD8DCE0),
    neutralLine = Color(0xffEDEDED),
    neutralOffWhite = Color(0xffF7F7FC),
    neutralOffWhiteDisabled = Color(0xffF9F9FC),
    neutralWhite = Color(0xffFFFFFF),
    accentDanger = Color(0xffE94242),
    accentWarning = Color(0xffFDCF41),
    accentSuccess = Color(0xff2CC069),
    accentSafe = Color(0xff7BCBCF),
    blueColor = Color(0xff166FF6),
    purpleColor = Color(0xffD2D5F9),
    primaryDefault = Color(0xff5f2eea),
    tabUnselectedContentColor = Color(0xff666666)
)

val LocalColorScheme = staticCompositionLocalOf {
    LightColorScheme
}
