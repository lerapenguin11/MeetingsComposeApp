package com.example.composeprotject.di

import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.auth.AuthCodeViewModel
import com.example.composeprotject.viewModel.auth.AuthPhoneNumberViewModel
import com.example.composeprotject.viewModel.auth.CreateProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel() }
    viewModel { AuthPhoneNumberViewModel() }
    viewModel { AuthCodeViewModel() }
    viewModel { CreateProfileViewModel() }

}