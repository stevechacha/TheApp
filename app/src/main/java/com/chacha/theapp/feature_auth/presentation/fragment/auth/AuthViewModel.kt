package com.chacha.theapp.feature_auth.presentation.fragment.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.asEvent
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class AuthViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _uiState = MutableLiveData<AuthUIState>()
    val uiState: LiveData<AuthUIState> = _uiState

    private val _interactions = MutableLiveData<Event<AuthActions>>()
    val interactions: LiveData<Event<AuthActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(AuthUIState.Error(exception.handleThrowable()))

        val errorPair = exception.handleThrowable()
    }

    fun navigateToLogin() {
        val toLogin = AuthFragmentDirections.authFragmentToLoginFragment()
        val action = AuthActions.Navigate(toLogin)
        _interactions.postValue(action.asEvent())

    }

    fun navigateToRegister() {
        val toRegister = AuthFragmentDirections.authFragmentToRegisterFragment()
        val action = AuthActions.Navigate(toRegister)
        _interactions.postValue(action.asEvent())

    }

}

sealed class AuthActions {
    data class Navigate(val destination: NavDirections) : AuthActions()
}

sealed class AuthUIState {

    object Loading : AuthUIState()

    data class Error(val title: String = "Try again") : AuthUIState()

    data class LimitError(val message: String) : AuthUIState()
}