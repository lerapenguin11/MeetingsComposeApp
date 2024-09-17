package com.example.composeprotject.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.core.content.ContextCompat
import com.example.composeprotject.R
import com.example.domain.model.interest.Interest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun readCountryDataFromJson(context: Context): Map<String, CountryData> {
    val inputStream = context.resources.openRawResource(R.raw.country_data)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    val type = object : TypeToken<Map<String, CountryData>>() {}.type
    return Gson().fromJson(jsonString, type)
}

fun getUserFullName(userName: String, userSurname: String?): String {
    return if (userSurname.isNullOrEmpty()) {
        userName
    } else {
        "$userName $userSurname"
    }
}

fun lineBreakInAddress(short: String, full: String): String {
    val cropAddress = full.replace(short, "")
    val address = buildAnnotatedString {
        append(cropAddress)
        append("\n")
        append(short)
    }
    return address.text
}

fun checkingUserNoSuchInterest(userInterests: List<Interest>, id: Int): Boolean {
    return userInterests.none { it.id == id }
}

fun hasPermissions(context: Context, vararg permissions: String): Boolean {
    for (permission in permissions) {
        if (ContextCompat.checkSelfPermission(
                context,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
    }
    return true
}