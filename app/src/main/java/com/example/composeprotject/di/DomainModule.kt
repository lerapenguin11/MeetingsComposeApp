package com.example.composeprotject.di

import com.example.composeprotject.domain.usecase.community.GetCommunitiesUseCase
import com.example.composeprotject.domain.usecase.details.GetCommunityByIdUseCase
import com.example.composeprotject.domain.usecase.details.GetEventByIdUseCase
import com.example.composeprotject.domain.usecase.event.GetEventsUseCase
import com.example.composeprotject.domain.usecase.event.GetMyEventsUseCase
import com.example.composeprotject.domain.usecase.user.GetInfoUserProfileUseCase
import com.example.composeprotject.domain.usecase.user.GetShortInfoUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetCommunitiesUseCase> { GetCommunitiesUseCase(repository = get()) }
    factory<GetShortInfoUserUseCase> { GetShortInfoUserUseCase(repository = get()) }
    factory<GetInfoUserProfileUseCase> { GetInfoUserProfileUseCase(repository = get()) }
    factory<GetEventsUseCase> { GetEventsUseCase(repository = get()) }
    factory<GetMyEventsUseCase> { GetMyEventsUseCase(repository = get()) }
    factory<GetEventByIdUseCase> { GetEventByIdUseCase(repository = get()) }
    factory<GetCommunityByIdUseCase> { GetCommunityByIdUseCase(repository = get()) }
}