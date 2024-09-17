package com.example.network.di

import com.example.network.api.EventApi
import org.koin.dsl.module

val serviceModule = module {
    val networkModule by lazy {
        NetworkModule()
    }
    single { networkModule.getRetrofit().create(EventApi::class.java) }
}