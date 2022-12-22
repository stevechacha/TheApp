package com.chacha.theapp.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chacha.theapp.core.data.local.entity.Accounts

@Dao
interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: Accounts)

    @Query("SELECT * FROM accounts")
    fun getAllAccounts(): List<Accounts>


    @Query("SELECT * FROM accounts WHERE accountNumber = :accountNumber")
    fun getAccount(accountNumber: String): Accounts

    @Delete
    fun deleteAccount(account: Accounts)


    /* Others */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllAccounts(account: List<Accounts>): List<Long>

    @Query("SELECT * FROM accounts WHERE id = :id")
    fun getAccount(id: Int): Accounts?

    @Query("SELECT * FROM accounts WHERE id = :id")
    fun getLiveAccount(id: Int?): LiveData<Accounts>

    @Query("SELECT COUNT(id) FROM accounts")
    fun getDataCount(): Int

    @Update
    fun updateAccount(account: Accounts?)

    @Update
    suspend fun updateAccount(account: LiveData<Accounts>)


    @Query("DELETE FROM accounts")
    fun deleteAll()

}