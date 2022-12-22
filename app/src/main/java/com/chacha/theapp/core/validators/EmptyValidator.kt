package com.chacha.theapp.core.validators


import com.chacha.theapp.R
import com.chacha.theapp.core.validators.base.BaseValidator
import com.chacha.theapp.core.validators.base.ValidateResult




class EmptyValidator(val input: String) : BaseValidator() {
    override fun validate(): ValidateResult {
        val isValid = input.isNotEmpty()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_empty_field
        )
    }
}