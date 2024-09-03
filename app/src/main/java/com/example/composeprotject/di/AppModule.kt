package com.example.composeprotject.di

import com.example.composeprotject.viewModel.InterestsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::InterestsViewModel)
}
