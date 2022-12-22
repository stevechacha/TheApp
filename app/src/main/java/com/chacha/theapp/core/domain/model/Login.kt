package com.chacha.theapp.core.domain.model

import androidx.annotation.Keep

@Keep
data class Login(
    val PhoneNumber: String?,
    val Email: String?,
    val Password: String
)
