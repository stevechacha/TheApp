package com.chacha.theapp.core.data.local.entity

import androidx.room.Entity

@Entity(tableName = "savings_account")
data class SavingsAccount(
    var balance: Double,
    var interestRate: Double,
    var accountType: String
){
    fun deposit(amount: Double){
        balance += amount
    }
    fun withdraw(amount: Double){
        balance -= amount
    }

    fun calculateInterest(): Double{
        return balance* interestRate
    }
}
