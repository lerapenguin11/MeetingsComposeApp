package com.example.composeprotject.di

import com.example.composeprotject.domain.usecase.community.GetCommunitiesUseCase
import com.example.composeprotject.domain.usecase.user.GetShortInfoUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetCommunitiesUseCase> { GetCommunitiesUseCase(repository = get()) }
    factory<GetShortInfoUserUseCase> { GetShortInfoUserUseCase(repository = get()) }
}