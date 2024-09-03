package com.example.domain.di

import com.example.domain.usecase.interest.GetInterestInteractor
import com.example.domain.usecase.interest.GetInterestsUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    single<GetInterestsUseCase> { GetInterestInteractor(repository = get()) }
}