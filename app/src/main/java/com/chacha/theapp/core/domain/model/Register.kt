package com.chacha.theapp.core.domain.model

import androidx.annotation.Keep

@Keep
data class Register(
    val Email: String,
    val PhoneNumber: String,
    val Password: String,
    val IDNumber: String
)
