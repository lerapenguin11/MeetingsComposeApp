package com.example.composeprotject.di_old

import com.example.composeprotject.viewModel_old.MyEventViewModel
import com.example.composeprotject.viewModel_old.ProfileViewModel
import com.example.composeprotject.viewModel_old.SplashScreenViewModel
import com.example.composeprotject.viewModel_old.auth.AuthCodeViewModel
import com.example.composeprotject.viewModel_old.auth.AuthPhoneNumberViewModel
import com.example.composeprotject.viewModel_old.auth.CreateProfileViewModel
import com.example.composeprotject.viewModel_old.details.CommunityDetailsViewModel
import com.example.composeprotject.viewModel_old.details.EventDetailsViewModel
import com.example.composeprotject.viewModel_old.nav.CommunityViewModel
import com.example.composeprotject.viewModel_old.nav.EventViewModel
import com.example.composeprotject.viewModel_old.nav.StillViewModel
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