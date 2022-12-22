package com.chacha.theapp.core.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import com.chacha.theapp.feature_accounts.presentation.fragment.account.AccountUIState
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class HomeViewModel : ViewModel() {
    private val _uiState = MutableLiveData<HomeUIState>()
    val uiState: LiveData<HomeUIState> = _uiState

    private val _interaction = MutableLiveData<Event<HomeActions>>()
    val interaction: LiveData<Event<HomeActions>> = _interaction

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(HomeUIState.Error(exception.handleThrowable()))

    }


}

sealed class HomeActions{
    data class Navigate(val destination: NavDirections) : HomeActions()
}
sealed class HomeUIState{
    object Loading : HomeUIState()

    data class Error(val message: String = "Try again") : HomeUIState()

    data class LimitError(val message: String) : HomeUIState()

    data class Message(val message: String): HomeUIState()
}