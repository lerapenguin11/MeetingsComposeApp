package com.example.data.old.di

import com.example.data.old.repository.CommunityDetailsRepositoryImpl
import com.example.data.old.repository.CommunityRepositoryImpl
import com.example.data.old.repository.EventDetailsRepositoryImpl
import com.example.data.old.repository.EventRepositoryImpl
import com.example.data.old.repository.UserRepositoryImpl
import com.example.data.old.repository.VerificationRepositoryImpl
import com.example.domain.old.repository.CommunityDetailsRepository
import com.example.domain.old.repository.CommunityRepository
import com.example.domain.old.repository.EventDetailsRepository
import com.example.domain.old.repository.EventRepository
import com.example.domain.old.repository.UserRepository
import com.example.domain.old.repository.VerificationRepository
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