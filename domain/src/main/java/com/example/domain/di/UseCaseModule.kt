package com.example.domain.di

import com.example.domain.usecase.GetPeopleUseCase
import com.example.domain.usecase.InteractorLoadPeopleByCategoryId
import com.example.domain.usecase.InteractorLoadPeopleByEventId
import com.example.domain.usecase.combineUseCase.InteractorFullEventDetailsInfo
import com.example.domain.usecase.combineUseCase.InteractorFullInfoCommunityDetails
import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.details.GetCommunityDetailsUseCase
import com.example.domain.usecase.details.GetEventDetailsInfoUseCase
import com.example.domain.usecase.details.InteractorLoadCommunityDetails
import com.example.domain.usecase.details.InteractorLoadEventDetailsInfo
import com.example.domain.usecase.details.InteractorLoadEventsByCommunityId
import com.example.domain.usecase.event.GetMainInfoUseCase
import com.example.domain.usecase.event.InteractorLoadMainInfo
import com.example.domain.usecase.getData.GetCommunity
import com.example.domain.usecase.getData.GetCommunityDetails
import com.example.domain.usecase.getData.GetEventDetails
import com.example.domain.usecase.getData.GetEventsByCategory
import com.example.domain.usecase.getData.GetEventsByCommunityId
import com.example.domain.usecase.getData.GetEventsClosest
import com.example.domain.usecase.getData.GetFilteredEventsByCategory
import com.example.domain.usecase.getData.GetPeople
import com.example.domain.usecase.interest.AddUserInterestsUseCase
import com.example.domain.usecase.interest.AddUserInterestsUseCaseInteractor
import com.example.domain.usecase.interest.GetInterestInteractor
import com.example.domain.usecase.interest.GetInterestsUseCase
import com.example.domain.usecase.location.GetCurrentLocationUseCase
import com.example.domain.usecase.location.GetCurrentLocationUseCaseImpl
import com.example.domain.usecase.signUp.SendConfirmationCodeUseCase
import com.example.domain.usecase.signUp.SendConfirmationCodeUseCaseImpl
import com.example.domain.usecase.signUp.SendPhoneVerificationCodeUseCase
import com.example.domain.usecase.signUp.SendPhoneVerificationCodeUseCaseImpl
import com.example.domain.usecase.store.ReadOnBoardingInterestStateUseCase
import com.example.domain.usecase.store.ReadOnBoardingInterestStateUseCaseImpl
import com.example.domain.usecase.store.ReadUserCityUseCase
import com.example.domain.usecase.store.ReadUserCityUseCaseImpl
import com.example.domain.usecase.store.SaveOnBoardingInterestStateUseCase
import com.example.domain.usecase.store.SaveOnBoardingInterestStateUseCaseImpl
import com.example.domain.usecase.store.SaveUserCityUseCase
import com.example.domain.usecase.store.SaveUserCityUseCaseImpl
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
    single<ReadOnBoardingInterestStateUseCase> { ReadOnBoardingInterestStateUseCaseImpl(repository = get()) }
    single<SaveOnBoardingInterestStateUseCase> { SaveOnBoardingInterestStateUseCaseImpl(repository = get()) }
    single { GetEventDetailsInfoUseCase() }
    single { InteractorFullEventDetailsInfo() }
    single { InteractorLoadEventDetailsInfo() }
    single { InteractorLoadEventsByCommunityId() }
    single { GetEventDetails() }
    single { GetEventsByCommunityId() }
    single { GetCommunityDetailsUseCase() }
    single { InteractorLoadCommunityDetails() }
    single { GetCommunityDetails() }
    single { InteractorFullInfoCommunityDetails() }
    single { GetPeopleUseCase() }
    single { InteractorLoadPeopleByEventId() }
    single { InteractorLoadPeopleByCategoryId() }
    single { GetPeople() }
    single<SendPhoneVerificationCodeUseCase> { SendPhoneVerificationCodeUseCaseImpl(repository = get()) }
    single<SendConfirmationCodeUseCase> { SendConfirmationCodeUseCaseImpl(repository = get()) }
    single<GetCurrentLocationUseCase> { GetCurrentLocationUseCaseImpl(repository = get()) }
    single<SaveUserCityUseCase> { SaveUserCityUseCaseImpl(repository = get()) }
    single<ReadUserCityUseCase> { ReadUserCityUseCaseImpl(repository = get()) }
}