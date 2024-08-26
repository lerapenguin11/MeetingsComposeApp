package com.example.composeprotject.ui.component.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun eventDate(timestamp: Long): String {
    val currentTime = System.currentTimeMillis() / 1000L
    val timeDifference = timestamp - currentTime

    val formattedDate = when {
        timeDifference < 86400 -> "Сегодня" // Менее 24 часов до события
        timeDifference < 172800 -> "Завтра" // Менее 48 часов до события
        else -> {
            val date = Date(timestamp * 1000L)
            val dateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
            dateFormat.format(date)
        }
    }
    return formattedDate
}
