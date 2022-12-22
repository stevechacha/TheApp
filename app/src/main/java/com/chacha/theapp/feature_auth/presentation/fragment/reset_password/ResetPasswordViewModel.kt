package com.chacha.theapp.feature_auth.presentation.fragment.reset_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import com.chacha.theapp.feature_auth.presentation.fragment.reset_pin.ResetPinUIState
import com.chacha.theapp.core.util.Event

class ResetPasswordViewModel : ViewModel() {

    private val _uiState = MutableLiveData<ResetPasswordUIState>()
    val uiState : LiveData<ResetPasswordUIState> = _uiState

    private val _interactions = MutableLiveData<Event<ResetPasswordActions>>()
    val interactions : LiveData<Event<ResetPasswordActions>> = _interactions

}

sealed class ResetPasswordActions{
    data class Navigate(val destination: NavDirections): ResetPasswordActions()
}

sealed class ResetPasswordUIState{

    object Loading: ResetPasswordUIState()

    data class Error(val title: String = "Try gain"): ResetPasswordUIState()
}