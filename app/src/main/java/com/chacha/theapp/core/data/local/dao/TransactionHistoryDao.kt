package com.chacha.theapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chacha.theapp.core.data.local.entity.TransactionHistoryEntity


@Dao
interface TransactionHistoryDao {
    @Query("SELECT * FROM transaction_history")
    fun getAllTransactions(): List<TransactionHistoryEntity>

    @Query("SELECT * FROM transaction_history WHERE transactionNumber = :transactionNumber")
    fun getTransaction(transactionNumber: String): TransactionHistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionHistoryEntity)

    @Delete
    fun deleteTransaction(transaction: TransactionHistoryEntity)
}