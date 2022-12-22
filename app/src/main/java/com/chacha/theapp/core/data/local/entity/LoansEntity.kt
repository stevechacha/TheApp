package com.chacha.theapp.core.data.local.entity

import androidx.room.PrimaryKey

data class LoansEntity(
    @PrimaryKey val id: Int,
    val memberId: Int,
    val loanAmount: Double,
    val interestRate: Double,
    val repayDuration: Int,
    val outstandingBalance: Double
)
