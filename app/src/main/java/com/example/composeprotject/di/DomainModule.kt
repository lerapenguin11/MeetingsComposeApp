package com.example.composeprotject.di

import com.example.composeprotject.domain.usecase.GetCommunitiesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetCommunitiesUseCase> { GetCommunitiesUseCase(repository = get()) }
}