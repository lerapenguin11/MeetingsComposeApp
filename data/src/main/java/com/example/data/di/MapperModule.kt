package com.example.data.di

import com.example.data.mappers.InterestsMapper
import org.koin.dsl.module

internal val mapperModule = module {
    single<InterestsMapper> { InterestsMapper() }
}