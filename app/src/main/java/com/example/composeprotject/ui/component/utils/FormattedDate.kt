package com.example.composeprotject.ui.component.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

//TODO: переписать
fun formatEventDate(timestamp: Long, formatTime: FormatEventDateState): String {
    val date = Date(timestamp * 1000)
    return formatEventDate(date = date, formatTime = formatTime)
}

private fun formatEventDate(date: Date, formatTime: FormatEventDateState): String {
    val now = Date()
    val tomorrow = Date(now.time + 24 * 60 * 60 * 1000)

    val dateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    return when {
        date.after(now) && date.before(tomorrow) -> "завтра${formatTime.format}${
            timeFormat.format(
                date
            )
        }"

        date.after(now) -> "сегодня${formatTime.format}${timeFormat.format(date)}"
        else -> "${dateFormat.format(date)}${formatTime.format}${timeFormat.format(date)}"
    }
}

enum class FormatEventDateState(val format: String) {
    DATE(format = ""),
    DATE_TIME(format = ", "),
    DATE_IN_TIME(format = " в ")
}

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
        timeDifference < 86400 -> "Сегодня ${formatTime(timestamp)}"
        timeDifference < 172800 -> "Завтра, ${formatTime(timestamp)}"
        else -> "${formatDate(timestamp)}, ${formatTime(timestamp)}"
    }
}

fun eventDetailsDat(timestamp: Long): String {

    val currentTimeMillis = System.currentTimeMillis()
    val timeStamp = Timestamp(currentTimeMillis)

    val currentTime = timeStamp.time / 1000L
    val timeDifference = timestamp - currentTime

    return when {
        abs(timeDifference) < 86400 -> "Сегодня в ${
            formatTime(
                timestamp
            )
        }"

        abs(timeDifference) < 172800 -> "Завтра в ${formatTime(timestamp)}"
        else -> "${formatDate(timestamp)} в ${formatTime(timestamp)}"
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
