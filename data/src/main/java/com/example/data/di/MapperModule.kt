package com.example.data.di

import com.example.data.mappers.CommunityMapper
import com.example.data.mappers.EventsMapper
import com.example.data.mappers.InterestsMapper
import com.example.data.mappers.PeopleMapper
import org.koin.dsl.module

internal val mapperModule = module {
    single<InterestsMapper> { InterestsMapper() }
    single<EventsMapper> { EventsMapper() }
    single<CommunityMapper> { CommunityMapper() }
    single<PeopleMapper> { PeopleMapper() }
}