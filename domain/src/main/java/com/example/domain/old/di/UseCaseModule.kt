package com.example.domain.old.di

import com.example.domain.old.usecase.community.GetCommunitiesInteractor
import com.example.domain.old.usecase.community.GetCommunitiesUseCase
import com.example.domain.old.usecase.details.GetCommunityByIdInteractor
import com.example.domain.old.usecase.details.GetCommunityByIdUseCase
import com.example.domain.old.usecase.details.GetEventByIdInteractor
import com.example.domain.old.usecase.details.GetEventByIdUseCase
import com.example.domain.old.usecase.event.GetEventsUseCase
import com.example.domain.old.usecase.event.GetEventsInteractor
import com.example.domain.old.usecase.event.GetMyEventsInteractor
import com.example.domain.old.usecase.event.GetMyEventsUseCase
import com.example.domain.old.usecase.user.GetInfoUserProfileInteractor
import com.example.domain.old.usecase.user.GetInfoUserProfileUseCase
import com.example.domain.old.usecase.user.GetShortInfoUserInteractor
import com.example.domain.old.usecase.user.GetShortInfoUserUseCase
import com.example.domain.old.usecase.verfication.PostPhoneNumberInteractor
import com.example.domain.old.usecase.verfication.PostPhoneNumberUseCase
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