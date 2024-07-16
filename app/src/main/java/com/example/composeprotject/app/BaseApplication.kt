package com.example.composeprotject.app

import android.app.Application
import com.example.composeprotject.di.appModule
import com.example.composeprotject.utils.CountryData
import com.example.composeprotject.utils.readCountryDataFromJson
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    val countryData: Map<String, CountryData> by lazy { readCountryDataFromJson(this) } //TODO

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}