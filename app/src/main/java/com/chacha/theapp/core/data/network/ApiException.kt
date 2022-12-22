package com.chacha.theapp.core.data.network

class ApiException(val statusCode: Int = 0, val statusMessage: String) : Throwable(statusMessage)