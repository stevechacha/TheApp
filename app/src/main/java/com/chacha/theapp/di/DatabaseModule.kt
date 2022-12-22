package com.chacha.theapp.di

import androidx.room.Room
import com.chacha.theapp.core.data.local.dao.AccountsDao
import com.chacha.theapp.core.data.local.dao.TransactionsDao
import com.chacha.theapp.core.data.local.database.FinanceDatabase
import com.chacha.theapp.core.util.Constants.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            FinanceDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single <AccountsDao>{
        val database = get<FinanceDatabase>()
        database.accountsDao()
    }

    single <TransactionsDao>{
        val database = get<FinanceDatabase>()
        database.transactionsDao
    }

    single {
        val database = get<FinanceDatabase>()
        database.transactionHistoryDao
    }

}