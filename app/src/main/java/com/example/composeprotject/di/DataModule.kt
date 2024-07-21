package com.example.composeprotject.di

import com.example.composeprotject.data.mock.MockCommunityData
import com.example.composeprotject.data.mock.MockCommunityDetails
import com.example.composeprotject.data.mock.MockEventData
import com.example.composeprotject.data.mock.MockEventDetailsData
import com.example.composeprotject.data.mock.MockUserData
import com.example.composeprotject.data.repository.CommunityDetailsRepositoryImpl
import com.example.composeprotject.data.repository.CommunityRepositoryImpl
import com.example.composeprotject.data.repository.EventDetailsRepositoryImpl
import com.example.composeprotject.data.repository.EventRepositoryImpl
import com.example.composeprotject.data.repository.UserRepositoryImpl
import com.example.domain.repository.CommunityDetailsRepository
import com.example.domain.repository.CommunityRepository
import com.example.domain.repository.EventDetailsRepository
import com.example.domain.repository.EventRepository
import com.example.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<MockUserData> { MockUserData() }
    single<MockCommunityData> { MockCommunityData() }
    single<MockEventData> { MockEventData() }
    single<MockEventDetailsData> { MockEventDetailsData() }
    single<MockCommunityDetails> { MockCommunityDetails() }

    single<com.example.domain.repository.CommunityRepository> { CommunityRepositoryImpl(mock = get()) }
    single<com.example.domain.repository.UserRepository> { UserRepositoryImpl(mock = get()) }
    single<com.example.domain.repository.EventRepository> { EventRepositoryImpl(mock = get()) }
    single<com.example.domain.repository.EventDetailsRepository> { EventDetailsRepositoryImpl(mock = get()) }
    single<com.example.domain.repository.CommunityDetailsRepository> { CommunityDetailsRepositoryImpl(mock = get()) }
}