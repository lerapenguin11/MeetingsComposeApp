package com.example.data.di

import com.example.data.repository.InterestRepositoryImpl
import com.example.domain.repository.interest.InterestRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<InterestRepository> { InterestRepositoryImpl(mapper = get(), dao = get()) }
}