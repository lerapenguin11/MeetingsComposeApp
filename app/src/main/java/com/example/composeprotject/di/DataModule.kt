package com.example.composeprotject.di

import com.example.composeprotject.data.mock.MockCommunityData
import com.example.composeprotject.data.mock.MockUserData
import com.example.composeprotject.data.repository.CommunityRepositoryImpl
import com.example.composeprotject.data.repository.UserRepositoryImpl
import com.example.composeprotject.domain.repository.CommunityRepository
import com.example.composeprotject.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<MockUserData> { MockUserData() }
    single<MockCommunityData> { MockCommunityData() }
    single<CommunityRepository> { CommunityRepositoryImpl(mock = get()) }
    single<UserRepository> { UserRepositoryImpl(mock = get()) }
}