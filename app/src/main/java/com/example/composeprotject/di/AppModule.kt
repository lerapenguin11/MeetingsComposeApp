package com.example.composeprotject.di

import com.example.composeprotject.viewModel.CommunityDetailsViewModel
import com.example.composeprotject.viewModel.EditUserViewModel
import com.example.composeprotject.viewModel.EventDetailsViewModel
import com.example.composeprotject.viewModel.InterestsViewModel
import com.example.composeprotject.viewModel.LocationOnboardingViewModel
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.PeopleViewModel
import com.example.composeprotject.viewModel.ProfileViewModel
import com.example.composeprotject.viewModel.SingUpViewModel
import com.example.composeprotject.viewModel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::InterestsViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::EventDetailsViewModel)
    viewModelOf(::CommunityDetailsViewModel)
    viewModelOf(::PeopleViewModel)
    viewModelOf(::SingUpViewModel)
    viewModelOf(::LocationOnboardingViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::EditUserViewModel)
}
