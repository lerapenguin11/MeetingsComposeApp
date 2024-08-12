package com.example.domain.di

import com.example.domain.usecase.community.GetCommunitiesInteractor
import com.example.domain.usecase.community.GetCommunitiesUseCase
import com.example.domain.usecase.details.GetCommunityByIdInteractor
import com.example.domain.usecase.details.GetCommunityByIdUseCase
import com.example.domain.usecase.details.GetEventByIdInteractor
import com.example.domain.usecase.details.GetEventByIdUseCase
import com.example.domain.usecase.event.GetEventsUseCase
import com.example.domain.usecase.event.GetEventsInteractor
import com.example.domain.usecase.event.GetMyEventsInteractor
import com.example.domain.usecase.event.GetMyEventsUseCase
import com.example.domain.usecase.user.GetInfoUserProfileInteractor
import com.example.domain.usecase.user.GetInfoUserProfileUseCase
import com.example.domain.usecase.user.GetShortInfoUserInteractor
import com.example.domain.usecase.user.GetShortInfoUserUseCase
import com.example.domain.usecase.verfication.PostPhoneNumberInteractor
import com.example.domain.usecase.verfication.PostPhoneNumberUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    single<GetCommunitiesUseCase> { GetCommunitiesInteractor(repository = get()) }
    single<GetEventByIdUseCase> { GetEventByIdInteractor(repository = get()) }
    single<GetCommunityByIdUseCase> { GetCommunityByIdInteractor(repository = get()) }
    single<GetEventsUseCase> { GetEventsInteractor(repository = get()) }
    single<GetMyEventsUseCase> { GetMyEventsInteractor(repository = get()) }
    single<GetInfoUserProfileUseCase> { GetInfoUserProfileInteractor(repository = get()) }
    single<GetShortInfoUserUseCase> { GetShortInfoUserInteractor(repository = get()) }
    single<PostPhoneNumberUseCase> { PostPhoneNumberInteractor(repository = get()) }
}