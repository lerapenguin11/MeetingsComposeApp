package com.example.composeprotject.di

import com.example.composeprotject.viewModel.InterestsViewModel
import com.example.composeprotject.viewModel.MainViewModel
import com.example.composeprotject.viewModel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::InterestsViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::SplashViewModel)
}
