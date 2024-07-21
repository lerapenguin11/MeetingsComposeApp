package com.example.data.di

import org.koin.dsl.module

internal val repositoryModule = module {
    single<com.example.domain.repository.CommunityRepository> {
        com.example.data.repository.CommunityRepositoryImpl(
            mock = get()
        )
    }
    single<com.example.domain.repository.UserRepository> {
        com.example.data.repository.UserRepositoryImpl(
            mock = get()
        )
    }
    single<com.example.domain.repository.EventRepository> {
        com.example.data.repository.EventRepositoryImpl(
            mock = get()
        )
    }
    single<com.example.domain.repository.EventDetailsRepository> {
        com.example.data.repository.EventDetailsRepositoryImpl(
            mock = get()
        )
    }
    single<com.example.domain.repository.CommunityDetailsRepository> {
        com.example.data.repository.CommunityDetailsRepositoryImpl(
            mock = get()
        )
    }
}