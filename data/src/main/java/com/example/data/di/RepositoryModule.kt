package com.example.data.di

import com.example.data.repository.CommunityDetailsRepositoryImpl
import com.example.data.repository.CommunityRepositoryImpl
import com.example.data.repository.EventDetailsRepositoryImpl
import com.example.data.repository.EventRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.data.repository.VerificationRepositoryImpl
import com.example.domain.repository.CommunityDetailsRepository
import com.example.domain.repository.CommunityRepository
import com.example.domain.repository.EventDetailsRepository
import com.example.domain.repository.EventRepository
import com.example.domain.repository.UserRepository
import com.example.domain.repository.VerificationRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<CommunityRepository> {
        CommunityRepositoryImpl(
            mock = get()
        )
    }
    single<UserRepository> {
        UserRepositoryImpl(
            mock = get()
        )
    }
    single<EventRepository> {
        EventRepositoryImpl(
            mock = get()
        )
    }
    single<EventDetailsRepository> {
        EventDetailsRepositoryImpl(
            mock = get()
        )
    }
    single<CommunityDetailsRepository> {
        CommunityDetailsRepositoryImpl(
            mock = get()
        )
    }
    single<VerificationRepository> {
        VerificationRepositoryImpl(
            //TODO
        )
    }
}