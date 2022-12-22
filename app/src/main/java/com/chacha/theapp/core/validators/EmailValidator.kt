package com.chacha.theapp.core.validators

import android.text.TextUtils
import com.chacha.theapp.R
import com.chacha.theapp.core.validators.base.BaseValidator
import com.chacha.theapp.core.validators.base.ValidateResult


class EmailValidator(val email: String) : BaseValidator() {
    override fun validate(): ValidateResult {
        val isValid =
            !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_email
        )
    }
}