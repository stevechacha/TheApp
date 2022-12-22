package com.chacha.theapp.feature_auth.presentation.fragment.reset_pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event

class ResetPinViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _uiState = MutableLiveData<ResetPinUIState>()
    val uiState : LiveData<ResetPinUIState> = _uiState

    private val _interactions = MutableLiveData<Event<ResetPinActions>>()
    val interactions : LiveData<Event<ResetPinActions>> = _interactions

}


sealed class ResetPinActions{
    data class Navigate(val destination: NavDirections): ResetPinActions()
}

sealed class ResetPinUIState{
    object Loading : ResetPinUIState()

    data class Error( val title: String = "Try again") : ResetPinUIState()

    data class Message(val message: String): ResetPinUIState()
}