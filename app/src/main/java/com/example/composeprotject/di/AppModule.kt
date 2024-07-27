package com.example.composeprotject.di

import com.example.composeprotject.viewModel.MyEventViewModel
import com.example.composeprotject.viewModel.ProfileViewModel
import com.example.composeprotject.viewModel.SplashScreenViewModel
import com.example.composeprotject.viewModel.auth.AuthCodeViewModel
import com.example.composeprotject.viewModel.auth.AuthPhoneNumberViewModel
import com.example.composeprotject.viewModel.auth.CreateProfileViewModel
import com.example.composeprotject.viewModel.details.CommunityDetailsViewModel
import com.example.composeprotject.viewModel.details.EventDetailsViewModel
import com.example.composeprotject.viewModel.nav.CommunityViewModel
import com.example.composeprotject.viewModel.nav.EventViewModel
import com.example.composeprotject.viewModel.nav.StillViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashScreenViewModel() }
    viewModel { AuthPhoneNumberViewModel(postPhoneNumberUseCase = get()) }
    viewModel { AuthCodeViewModel() }
    viewModel { CreateProfileViewModel() }
    viewModel { CommunityViewModel(getCommunitiesUseCase = get()) }
    viewModel { StillViewModel(getShortInfoUserUseCase = get()) }
    viewModel { ProfileViewModel(getInfoUserProfileUseCase = get()) }
    viewModel { EventViewModel(getEventsUseCase = get()) }
    viewModel { MyEventViewModel(getMyEventsUseCase = get()) }
    viewModel { EventDetailsViewModel(getEventByIdUseCase = get()) }
    viewModel { CommunityDetailsViewModel(getCommunityByIdUseCase = get()) }
}