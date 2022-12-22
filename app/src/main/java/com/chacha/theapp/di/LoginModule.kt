package com.chacha.theapp.di

import com.chacha.theapp.feature_auth.presentation.fragment.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory

val loginModule = module {
    single { MoshiConverterFactory.create(get()).asLenient() }

    viewModel { LoginViewModel(get()) }
}