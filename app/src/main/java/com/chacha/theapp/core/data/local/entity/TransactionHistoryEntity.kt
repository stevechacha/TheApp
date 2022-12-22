package com.chacha.theapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transaction_history")
data class TransactionHistoryEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val transactionAmount: Double,
    val transactionDate: Date,
    val transactionCurrency: String,
    val transactionNumber: Int,
    val transactionType: String,
    val transactionDescription: String,
    val transactionFee: Double?,
    val transactionParties: List<TransactionParty>
)



