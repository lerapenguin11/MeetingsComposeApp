package com.example.domain.old.di

import org.koin.dsl.module

val domainModule = module {
    includes(useCaseModule)
}