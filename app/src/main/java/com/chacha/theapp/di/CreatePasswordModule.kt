package com.chacha.theapp.di

import com.chacha.theapp.feature_accounts.presentation.fragment.account.AccountViewModel
import com.chacha.theapp.feature_auth.presentation.fragment.auth.AuthViewModel
import com.chacha.theapp.feature_auth.presentation.fragment.create_password.CreatePasswordViewModel
import com.chacha.theapp.feature_auth.presentation.fragment.create_pin.CreatePinViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.expense.ExpenseViewModel
import com.chacha.theapp.core.presentation.fragment.home.HomeViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory

val createPasswordModule = module {
    single { MoshiConverterFactory.create(get()).asLenient() }
    viewModel { CreatePasswordViewModel(get()) }

}



