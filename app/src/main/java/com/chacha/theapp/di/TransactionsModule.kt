package com.chacha.theapp.di

import com.chacha.theapp.feature_accounts.presentation.fragment.account.AccountViewModel
import com.chacha.theapp.feature_auth.presentation.fragment.auth.AuthViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.expense.ExpenseViewModel
import com.chacha.theapp.core.presentation.fragment.home.HomeViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementViewModel
import com.chacha.theapp.feature_transaction.fragment.transaction.TransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val transactionsModule = module {
    viewModel { TransactionViewModel() }

}



