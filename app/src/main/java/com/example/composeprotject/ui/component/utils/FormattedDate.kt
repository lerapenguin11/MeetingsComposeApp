package com.example.composeprotject.ui.component.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun eventDate(timestamp: Long): String {
    val currentTime = System.currentTimeMillis() / 1000L
    val timeDifference = timestamp - currentTime

    val formattedDate = when {
        timeDifference < 86400 -> "Сегодня"
        timeDifference < 172800 -> "Завтра"
        else -> {
            formatDate(timestamp = timestamp)
        }
    }
    return formattedDate
}

fun eventDetailsDate(timestamp: Long): String {
    val currentTime = System.currentTimeMillis() / 1000L
    val timeDifference = timestamp - currentTime

    return when {
        timeDifference < 86400 -> "Сегодня, ${formatTime(timestamp)}"
        timeDifference < 172800 -> "Завтра, ${formatTime(timestamp)}"
        else -> "${formatDate(timestamp)}, ${formatTime(timestamp)}"
    }
}

private fun formatDate(timestamp: Long): String {
    val date = Date(timestamp * 1000L)
    val dateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
    return dateFormat.format(date)
}

private fun formatTime(timestamp: Long): String {
    val date = Date(timestamp * 1000L)
    val timeFormat = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
    return timeFormat.format(date)
}

private const val TIME_PATTERN = "HH:mm"
private const val DATE_PATTERN = "dd MMMM"
