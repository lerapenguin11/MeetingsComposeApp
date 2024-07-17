package com.example.composeprotject.di

import com.example.composeprotject.data.repository.CommunityRepositoryImpl
import com.example.composeprotject.data.repository.UserRepositoryImpl
import com.example.composeprotject.domain.repository.CommunityRepository
import com.example.composeprotject.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<CommunityRepository> { CommunityRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl() }
}