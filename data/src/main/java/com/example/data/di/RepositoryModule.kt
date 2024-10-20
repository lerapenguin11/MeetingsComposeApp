package com.example.data.di

import com.example.data.repository.CommunityRepositoryImpl
import com.example.data.repository.DefaultLocationTrackerRepositoryImpl
import com.example.data.repository.EditUserRepositoryImpl
import com.example.data.repository.EventsRepositoryImpl
import com.example.data.repository.InterestRepositoryImpl
import com.example.data.repository.PeopleRepositoryImpl
import com.example.data.repository.SignUpRepositoryImpl
import com.example.data.repository.StoreRepositoryImpl
import com.example.data.repository.UserListsRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.community.CommunityRepository
import com.example.domain.repository.editUser.EditUserRepository
import com.example.domain.repository.event.EventRepository
import com.example.domain.repository.interest.InterestRepository
import com.example.domain.repository.location.LocationTrackerRepository
import com.example.domain.repository.people.PeopleRepository
import com.example.domain.repository.signUp.SignUpRepository
import com.example.domain.repository.store.StoreRepository
import com.example.domain.repository.user.UserRepository
import com.example.domain.repository.userLists.UserListsRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val repositoryModule = module {
    single<InterestRepository> { InterestRepositoryImpl(mapper = get(), dao = get()) }
    single<EventRepository> { EventsRepositoryImpl(mapper = get(), service = get()) }
    single<CommunityRepository> { CommunityRepositoryImpl(mapper = get(), service = get()) }
    single<StoreRepository> { StoreRepositoryImpl(context = get()) }
    single<PeopleRepository> { PeopleRepositoryImpl(mapper = get()) }
    single<SignUpRepository> { SignUpRepositoryImpl(service = get(), mapper = get()) }
    single<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(
            androidContext().applicationContext
        )
    }
    single<LocationTrackerRepository> {
        DefaultLocationTrackerRepositoryImpl(
            context = androidContext().applicationContext,
            fusedLocationProviderClient = get()
        )
    }
    single<UserRepository> { UserRepositoryImpl() }
    single<UserListsRepository> { UserListsRepositoryImpl() }
    single<EditUserRepository> { EditUserRepositoryImpl(context = androidContext().applicationContext) }
}