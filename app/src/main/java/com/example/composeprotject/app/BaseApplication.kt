package com.example.composeprotject.app

import android.app.Application
import com.example.composeprotject.utils.CountryData
import com.example.composeprotject.utils.readCountryDataFromJson

class BaseApplication : Application() {

    val countryData: Map<String, CountryData> by lazy { readCountryDataFromJson(this) }

    private lateinit var mInstance: BaseApplication

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
}