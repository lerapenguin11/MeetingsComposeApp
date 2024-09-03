package com.example.data.old.di

import org.koin.dsl.module

val dataModule = module {
    includes(mockModule)
    includes(repositoryModule)
}