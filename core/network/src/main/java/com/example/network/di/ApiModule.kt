package com.example.network.di

import com.example.network.api.CommunityApi
import com.example.network.api.EventApi
import org.koin.dsl.module

val serviceModule = module {
    val networkModule by lazy {
        NetworkModule()
    }
    single { networkModule.getRetrofit().create(EventApi::class.java) }
    single { networkModule.getRetrofit().create(CommunityApi::class.java) }
}