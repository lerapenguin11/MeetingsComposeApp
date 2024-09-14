package com.example.data.di

import com.example.data.repository.CommunityRepositoryImpl
import com.example.data.repository.EventsRepositoryImpl
import com.example.data.repository.InterestRepositoryImpl
import com.example.data.repository.PeopleRepositoryImpl
import com.example.data.repository.SignUpRepositoryImpl
import com.example.data.repository.StoreRepositoryImpl
import com.example.domain.repository.community.CommunityRepository
import com.example.domain.repository.event.EventRepository
import com.example.domain.repository.interest.InterestRepository
import com.example.domain.repository.people.PeopleRepository
import com.example.domain.repository.signUp.SignUpRepository
import com.example.domain.repository.store.StoreRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<InterestRepository> { InterestRepositoryImpl(mapper = get(), dao = get()) }
    single<EventRepository> { EventsRepositoryImpl(mapper = get(), dao = get()) }
    single<CommunityRepository> { CommunityRepositoryImpl(mapper = get()) }
    single<StoreRepository> { StoreRepositoryImpl(context = get()) }
    single<PeopleRepository> { PeopleRepositoryImpl(mapper = get()) }
    single<SignUpRepository> { SignUpRepositoryImpl() }
}