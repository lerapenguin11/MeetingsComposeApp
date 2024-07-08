package com.example.composeprotject.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class MeetSizeSystem(
    val sizeX1: Dp,
    val sizeX2: Dp,
    val sizeX3: Dp,
    val sizeX4: Dp,
    val sizeX5: Dp,
    val sizeX6: Dp,
    val sizeX7: Dp,
    val sizeX8: Dp,
    val sizeX9: Dp,
    val sizeX10: Dp,
    val sizeX12: Dp,
    val sizeX16: Dp,
    val sizeX20: Dp,
    val sizeX18: Dp,
    val sizeX24: Dp,
    val sizeX26: Dp,
    val sizeX50: Dp,
    val sizeX46: Dp,
    val sizeX40: Dp,
    val sizeX56: Dp,
    val sizeX30: Dp
)

val EventsLocalSizeSystem = MeetSizeSystem(
    sizeX1 = 1.dp,
    sizeX2 = 2.dp,
    sizeX3 = 3.dp,
    sizeX4 = 4.dp,
    sizeX5 = 5.dp,
    sizeX6 = 6.dp,
    sizeX7 = 7.dp,
    sizeX8 = 8.dp,
    sizeX9 = 9.dp,
    sizeX10 = 10.dp,
    sizeX12 = 12.dp,
    sizeX16 = 16.dp,
    sizeX20 = 20.dp,
    sizeX18 = 18.dp,
    sizeX24 = 24.dp,
    sizeX26 = 26.dp,
    sizeX50 = 50.dp,
    sizeX46 = 46.dp,
    sizeX40 = 40.dp,
    sizeX56 = 56.dp,
    sizeX30 = 30.dp
)

val LocalSizeSystem = staticCompositionLocalOf { EventsLocalSizeSystem }