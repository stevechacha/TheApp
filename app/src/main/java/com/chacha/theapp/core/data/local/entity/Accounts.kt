package com.chacha.theapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Accounts(
    @PrimaryKey(autoGenerate = false)
    val Id: Int,
    val accountHolderName: String,
    val accountNumber: String,
    val accountBalance: Double,
    val accountCurrency: String,
    val accountType: String,
    val accountIcon: Int,
    val accountTransactions: List<TransactionHistoryEntity>,
)
