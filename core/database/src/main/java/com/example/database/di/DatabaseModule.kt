package com.example.database.di

import com.example.database.db.MeetingDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        MeetingDatabase.getInstance(context = get())
    }
    single { get<MeetingDatabase>().getUserInterestDao() }
}