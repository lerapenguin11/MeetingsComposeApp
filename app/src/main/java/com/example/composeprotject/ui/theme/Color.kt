package com.example.composeprotject.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class MeetColorScheme(
    val primary: Color,
    val secondary: Color,
    val error: Color,
    val black: Color,
    val gray: Color,
    val backgroundColorPrimary: Brush,
    val backgroundColorSecondary: Brush,
    val darkGray: Color,
    val inactiveSwitch: Color,


    val disabledButton: Color,
    val brandDark: Color,
    val brandDarkInnerShadow: Color,
    val brandDefault: Color,
    val brandDarkMode: Color,
    val brandLight: Color,
    val brandBG: Color,
    val neutralActive: Color,
    val neutralDark: Color,
    val neutralBody: Color,
    val neutralWeak: Color,
    val neutralDisabled: Color,
    val neutralDisabled2: Color,
    val neutralLine: Color,
    val neutralOffWhite: Color,
    val neutralOffWhiteDisabled: Color,
    val neutralWhite: Color,
    val accentDanger: Color,
    val accentWarning: Color,
    val accentSuccess: Color,
    val accentSafe: Color,
    val blueColor: Color,
    val purpleColor: Color,
    val primaryDefault: Color,
    val tabUnselectedContentColor: Color
)

val LightColorScheme = MeetColorScheme(
    primary = Color(0xff9A10F0),
    secondary = Color(0xffF6F6FA),
    error = Color(0xffFEE7ED),
    black = Color(0xff000000),
    neutralDisabled = Color(0xffADB5BD),
    gray = Color(0xff9797AF),
    backgroundColorPrimary = Brush.horizontalGradient(
        listOf(
            Color(0xFFED3CCA), Color(0xFFDF34D2),
            Color(0xFFD02BD9), Color(0xFFBF22E1),
            Color(0xFFAE1AE8), Color(0xFF9A10F0),
            Color(0xFF8306F7), Color(0xFF6600FF)
        )
    ),
    backgroundColorSecondary = Brush.horizontalGradient(
        listOf(
            Color(0xFFFEF1FB), Color(0xFFFDF1FC),
            Color(0xFFFCF0FC), Color(0xFFFBF0FD),
            Color(0xFFF9EFFD), Color(0xFFF8EEFE),
            Color(0xFFF6EEFE), Color(0xFFF4EDFF)
        )
    ),
    darkGray = Color(0xff76778E),
    inactiveSwitch = Color(0xffEFEFEF),



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
