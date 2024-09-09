package com.example.data.di

import com.example.data.repository.CommunityRepositoryImpl
import com.example.data.repository.EventsRepositoryImpl
import com.example.data.repository.InterestRepositoryImpl
import com.example.domain.repository.community.CommunityRepository
import com.example.domain.repository.event.EventRepository
import com.example.domain.repository.interest.InterestRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<InterestRepository> { InterestRepositoryImpl(mapper = get(), dao = get()) }
    single<EventRepository> { EventsRepositoryImpl(mapper = get()) }
    single<CommunityRepository> { CommunityRepositoryImpl(mapper = get()) }
}