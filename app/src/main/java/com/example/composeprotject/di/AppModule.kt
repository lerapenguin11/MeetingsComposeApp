package com.example.composeprotject.di

import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.auth.AuthCodeViewModel
import com.example.composeprotject.viewModel.auth.AuthPhoneNumberViewModel
import com.example.composeprotject.viewModel.auth.CreateProfileViewModel
import com.example.composeprotject.viewModel.nav.CommunityViewModel
import com.example.composeprotject.viewModel.nav.StillViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel() }
    viewModel { AuthPhoneNumberViewModel() }
    viewModel { AuthCodeViewModel() }
    viewModel { CreateProfileViewModel() }
    viewModel { CommunityViewModel(getCommunitiesUseCase = get()) }
    viewModel { StillViewModel(getShortInfoUserUseCase = get()) }
}