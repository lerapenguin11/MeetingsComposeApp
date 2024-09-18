package com.example.composeprotject.app

import android.app.Application
import com.example.composeprotject.di.appModule
import com.example.composeprotject.utils.CountryData
import com.example.composeprotject.utils.readCountryDataFromJson
import com.example.data.di.dataModule
import com.example.database.di.databaseModule
import com.example.domain.di.domainModule
import com.example.network.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    val countryData: Map<String, CountryData> by lazy { readCountryDataFromJson(this) }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    appModule,
                    serviceModule,
                    databaseModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}