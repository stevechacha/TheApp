package com.chacha.theapp.feature_auth.presentation.fragment.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.chacha.theapp.core.data.network.TheAppApi
import com.chacha.theapp.feature_auth.presentation.fragment.login.LoginActions
import com.chacha.theapp.presentation.fragment.login.LoginFragmentDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.asEvent
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class RegisterViewModel(val api: TheAppApi) : ViewModel() {
    // TODO: Implement the ViewModel

    private val _uiState = MutableLiveData<RegisterUIState>()
    val uiState: LiveData<RegisterUIState> = _uiState

    private val _interactions = MutableLiveData<Event<RegisterActions>>()
    val interactions: LiveData<Event<RegisterActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(RegisterUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

    fun navigateToCreatePassword(
        email: String,
        phoneNumber: String,
        idNumber: String
    ){
        _uiState.postValue(RegisterUIState.Loading)
        viewModelScope.launch(exceptionHandler) {
            val toCreatePassword = RegisterFragmentDirections.registerFragmentToCreatePasswordFragment(
                email = email,
                phoneNumber = phoneNumber,
                idNumber = idNumber
            )
            val actions: RegisterActions = RegisterActions.Navigate(toCreatePassword)
            _interactions.postValue(actions.asEvent())
        }



    }

    fun navigateToLogin() {
        val toHome = RegisterFragmentDirections.registerFragmentToLoginFragment()
        val action = RegisterActions.Navigate(toHome)
       _interactions.postValue(action.asEvent())

    }
}

sealed class RegisterActions {
    data class Navigate(val destination: NavDirections) : RegisterActions()
}

sealed class RegisterUIState {

    object Loading : RegisterUIState()

    data class Error(val title: String = "Try again") : RegisterUIState()

    data class LimitError(val message: String) : RegisterUIState()
}