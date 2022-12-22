package com.chacha.theapp.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chacha.theapp.core.data.local.dao.AccountsDao
import com.chacha.theapp.core.data.local.dao.TransactionHistoryDao
import com.chacha.theapp.core.data.local.dao.TransactionsDao
import com.chacha.theapp.core.data.local.entity.Accounts
import com.chacha.theapp.core.data.local.entity.Transaction
import com.chacha.theapp.core.data.local.entity.TransactionHistoryEntity

@Database(
    entities = [
        Accounts::class,
        TransactionHistoryEntity::class,
        Transaction::class

    ],
    version = 1,
    exportSchema = false
)
abstract class FinanceDatabase : RoomDatabase(){
    abstract fun accountsDao(): AccountsDao
    abstract val transactionsDao: TransactionsDao
    abstract val transactionHistoryDao: TransactionHistoryDao
}