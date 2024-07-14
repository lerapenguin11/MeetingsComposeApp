package com.example.composeprotject.utils

import android.content.Context
import com.example.composeprotject.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun readCountryDataFromJson(context: Context): Map<String, CountryData> {
    val inputStream = context.resources.openRawResource(R.raw.country_data)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    val type = object : TypeToken<Map<String, CountryData>>() {}.type
    return Gson().fromJson(jsonString, type)
}