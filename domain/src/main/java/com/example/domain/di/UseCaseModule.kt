package com.example.domain.di

import com.example.domain.usecase.community.GetCommunitiesUseCase
import com.example.domain.usecase.details.GetCommunityByIdUseCase
import com.example.domain.usecase.details.GetEventByIdUseCase
import com.example.domain.usecase.event.GetEventsUseCase
import com.example.domain.usecase.event.GetMyEventsUseCase
import com.example.domain.usecase.user.GetInfoUserProfileUseCase
import com.example.domain.usecase.user.GetShortInfoUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val useCaseModule = module {
    factoryOf(::GetCommunitiesUseCase)
    factoryOf(::GetShortInfoUserUseCase)
    factoryOf(::GetInfoUserProfileUseCase)
    factoryOf(::GetEventsUseCase)
    factoryOf(::GetMyEventsUseCase)
    factoryOf(::GetEventByIdUseCase)
    factoryOf(::GetCommunityByIdUseCase)
}