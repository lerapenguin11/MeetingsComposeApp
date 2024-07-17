package com.example.composeprotject.di

import com.example.composeprotject.data.repository.CommunityRepositoryImpl
import com.example.composeprotject.domain.repository.CommunityRepository
import org.koin.dsl.module

val dataModule = module {
    single<CommunityRepository> { CommunityRepositoryImpl() }
}