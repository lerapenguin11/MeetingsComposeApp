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
import com.example.composeprotject.domain.repository.CommunityDetailsRepository
import com.example.composeprotject.domain.repository.CommunityRepository
import com.example.composeprotject.domain.repository.EventDetailsRepository
import com.example.composeprotject.domain.repository.EventRepository
import com.example.composeprotject.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<MockUserData> { MockUserData() }
    single<MockCommunityData> { MockCommunityData() }
    single<MockEventData> { MockEventData() }
    single<MockEventDetailsData> { MockEventDetailsData() }
    single<MockCommunityDetails> { MockCommunityDetails() }

    single<CommunityRepository> { CommunityRepositoryImpl(mock = get()) }
    single<UserRepository> { UserRepositoryImpl(mock = get()) }
    single<EventRepository> { EventRepositoryImpl(mock = get()) }
    single<EventDetailsRepository> { EventDetailsRepositoryImpl(mock = get()) }
    single<CommunityDetailsRepository> { CommunityDetailsRepositoryImpl(mock = get()) }
}