package com.chacha.theapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transaction")
data class Transaction(
    val deposits: List<Deposits>,
    val withdrawal: List<Withdrawals>,
    val transfer: List<Accounts>
)


@Entity(tableName = "deposits")
data class Deposits(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val depositAccount: String,
    val depositAmount: Double,
    val depositDate: Date,
)


@Entity(tableName = "withdrawals")
data class Withdrawals(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val accountWithdrawals: Int,
    val amountWithdrawals: Double,
    val withdrawalDate: Date
)
