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
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::AuthPhoneNumberViewModel)
    viewModelOf(::AuthCodeViewModel)
    viewModelOf(::CreateProfileViewModel)
    viewModelOf(::CommunityViewModel)
    viewModelOf(::StillViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::EventViewModel)
    viewModelOf(::MyEventViewModel)
    viewModelOf(::EventDetailsViewModel)
    viewModelOf(::CommunityDetailsViewModel)
}