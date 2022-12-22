package com.chacha.theapp.core.validators


import com.chacha.theapp.R
import com.chacha.theapp.core.validators.base.BaseValidator
import com.chacha.theapp.core.validators.base.ValidateResult




class PasswordValidator(val password: String) : BaseValidator() {
    private val minPasswordLength = 5
    private val maxPasswordLength = 10

    override fun validate(): ValidateResult {
        if (password.length < minPasswordLength)
            return ValidateResult(false, R.string.text_validation_error_min_pass_length)
        if (password.length > maxPasswordLength)
            return ValidateResult(false, R.string.text_validation_error_max_pass_length)
        return ValidateResult(true, R.string.text_validation_success)
    }
}