package com.example.domain.di

import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.event.GetMainInfoUseCase
import com.example.domain.usecase.event.InteractorLoadMainInfo
import com.example.domain.usecase.getData.GetCommunity
import com.example.domain.usecase.getData.GetEventsByCategory
import com.example.domain.usecase.getData.GetEventsClosest
import com.example.domain.usecase.getData.GetFilteredEventsByCategory
import com.example.domain.usecase.interest.AddUserInterestsUseCase
import com.example.domain.usecase.interest.AddUserInterestsUseCaseInteractor
import com.example.domain.usecase.interest.GetInterestInteractor
import com.example.domain.usecase.interest.GetInterestsUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    single<GetInterestsUseCase> { GetInterestInteractor(repository = get()) }
    single<AddUserInterestsUseCase> { AddUserInterestsUseCaseInteractor(repository = get()) }

    single { GetMainInfoUseCase() }
    single { InteractorLoadMainInfo() }
    single {
        GetEventsByCategory()
    }
    single { InteractorFullInfoMainScreen() }
    single { GetEventsClosest() }
    single { GetCommunity() }
    single { GetFilteredEventsByCategory() }
}