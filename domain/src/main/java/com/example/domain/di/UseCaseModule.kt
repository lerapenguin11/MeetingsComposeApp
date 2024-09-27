package com.example.domain.di

import com.example.domain.usecase.combineUseCase.InteractorFullEventDetailsInfo
import com.example.domain.usecase.combineUseCase.InteractorFullInfoCommunityDetails
import com.example.domain.usecase.combineUseCase.InteractorFullInfoMainScreen
import com.example.domain.usecase.combineUseCase.InteractorFullQueryParamLocal
import com.example.domain.usecase.combineUseCase.InteractorFullUserInfo
import com.example.domain.usecase.combineUseCase.InteractorReadIsShowSettingsLists
import com.example.domain.usecase.details.GetCommunityDetailsUseCase
import com.example.domain.usecase.details.GetEventDetailsInfoUseCase
import com.example.domain.usecase.details.InteractorLoadCommunityDetails
import com.example.domain.usecase.details.InteractorLoadEventDetailsInfo
import com.example.domain.usecase.details.InteractorLoadEventsByCommunityId
import com.example.domain.usecase.getData.GetCommunities
import com.example.domain.usecase.getData.GetCommunityDetails
import com.example.domain.usecase.getData.GetEventDetails
import com.example.domain.usecase.getData.GetEventsByCategory
import com.example.domain.usecase.getData.GetEventsByCommunityId
import com.example.domain.usecase.getData.GetEventsClosest
import com.example.domain.usecase.getData.GetFilteredEventsByCategory
import com.example.domain.usecase.getData.GetPeople
import com.example.domain.usecase.getData.GetUserCommunities
import com.example.domain.usecase.getData.GetUserEvents
import com.example.domain.usecase.getData.GetUserInfo
import com.example.domain.usecase.interest.AddUserInterestsUseCase
import com.example.domain.usecase.interest.AddUserInterestsUseCaseInteractor
import com.example.domain.usecase.interest.GetInterestInteractor
import com.example.domain.usecase.interest.GetInterestsUseCase
import com.example.domain.usecase.interest.GetUserInterestsUseCase
import com.example.domain.usecase.interest.GetUserInterestsUseCaseImpl
import com.example.domain.usecase.location.GetCurrentLocationUseCase
import com.example.domain.usecase.location.GetCurrentLocationUseCaseImpl
import com.example.domain.usecase.main.GetFilteredEventsUseCase
import com.example.domain.usecase.main.GetMainInfoUseCase
import com.example.domain.usecase.main.InteractorLoadFilteredEvents
import com.example.domain.usecase.main.InteractorLoadMainInfo
import com.example.domain.usecase.people.GetPeopleUseCase
import com.example.domain.usecase.people.InteractorLoadPeopleByCategoryId
import com.example.domain.usecase.people.InteractorLoadPeopleByEventId
import com.example.domain.usecase.signUp.SendConfirmationCodeUseCase
import com.example.domain.usecase.signUp.SendConfirmationCodeUseCaseImpl
import com.example.domain.usecase.signUp.SendPhoneVerificationCodeUseCase
import com.example.domain.usecase.signUp.SendPhoneVerificationCodeUseCaseImpl
import com.example.domain.usecase.store.city.ReadUserCityUseCase
import com.example.domain.usecase.store.city.ReadUserCityUseCaseImpl
import com.example.domain.usecase.store.city.SaveUserCityUseCase
import com.example.domain.usecase.store.city.SaveUserCityUseCaseImpl
import com.example.domain.usecase.store.settings.ReadIsEnableNotificationsUseCase
import com.example.domain.usecase.store.settings.ReadIsEnableNotificationsUseCaseImpl
import com.example.domain.usecase.store.settings.ReadIsIsShowMyCommunitiesUseCaseImpl
import com.example.domain.usecase.store.settings.ReadIsShowMyCommunitiesUseCase
import com.example.domain.usecase.store.settings.ReadIsShowMyEventsUseCase
import com.example.domain.usecase.store.settings.ReadIsShowMyEventsUseCaseImpl
import com.example.domain.usecase.store.settings.ReadOnBoardingInterestStateUseCase
import com.example.domain.usecase.store.settings.ReadOnBoardingInterestStateUseCaseImpl
import com.example.domain.usecase.store.settings.SaveIsEnableNotificationsUseCase
import com.example.domain.usecase.store.settings.SaveIsEnableNotificationsUseCaseImpl
import com.example.domain.usecase.store.settings.SaveIsShowMyCommunitiesUseCase
import com.example.domain.usecase.store.settings.SaveIsShowMyCommunitiesUseCaseImpl
import com.example.domain.usecase.store.settings.SaveIsShowMyEventsUseCase
import com.example.domain.usecase.store.settings.SaveIsShowMyEventsUseCaseImpl
import com.example.domain.usecase.store.settings.SaveOnBoardingInterestStateUseCase
import com.example.domain.usecase.store.settings.SaveOnBoardingInterestStateUseCaseImpl
import com.example.domain.usecase.store.token.DeleteAuthTokenUseCase
import com.example.domain.usecase.store.token.DeleteAuthTokenUseCaseImpl
import com.example.domain.usecase.store.token.ReadAuthTokenUseCase
import com.example.domain.usecase.store.token.ReadAuthTokenUseCaseImpl
import com.example.domain.usecase.store.token.SaveAuthTokenUseCase
import com.example.domain.usecase.store.token.SaveAuthTokenUseCaseImpl
import com.example.domain.usecase.user.GetUserInfoUseCase
import com.example.domain.usecase.user.InteractorLoadUserInfo
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
    single { GetCommunities() }
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
    single<SaveAuthTokenUseCase> { SaveAuthTokenUseCaseImpl(repository = get()) }
    single<ReadAuthTokenUseCase> { ReadAuthTokenUseCaseImpl(repository = get()) }
    single<GetUserInterestsUseCase> { GetUserInterestsUseCaseImpl(repository = get()) }
    single { InteractorFullQueryParamLocal() }
    single<SaveIsShowMyCommunitiesUseCase> { SaveIsShowMyCommunitiesUseCaseImpl(repository = get()) }
    single<ReadIsShowMyCommunitiesUseCase> { ReadIsIsShowMyCommunitiesUseCaseImpl(repository = get()) }
    single<SaveIsShowMyEventsUseCase> { SaveIsShowMyEventsUseCaseImpl(repository = get()) }
    single<ReadIsShowMyEventsUseCase> { ReadIsShowMyEventsUseCaseImpl(repository = get()) }
    single<SaveIsEnableNotificationsUseCase> { SaveIsEnableNotificationsUseCaseImpl(repository = get()) }
    single<ReadIsEnableNotificationsUseCase> { ReadIsEnableNotificationsUseCaseImpl(repository = get()) }
    single<InteractorReadIsShowSettingsLists> { InteractorReadIsShowSettingsLists() }
    single { GetUserInfoUseCase() }
    single { InteractorLoadUserInfo() }
    single { GetUserEvents() }
    single { GetUserCommunities() }
    single<GetUserInfo> { GetUserInfo() }
    single { InteractorFullUserInfo() }
    single<DeleteAuthTokenUseCase> { DeleteAuthTokenUseCaseImpl(repository = get()) }
    single<GetFilteredEventsUseCase> { GetFilteredEventsUseCase() }
    single<InteractorLoadFilteredEvents> { InteractorLoadFilteredEvents() }
}