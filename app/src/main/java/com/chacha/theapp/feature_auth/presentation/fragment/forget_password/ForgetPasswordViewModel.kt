package com.chacha.theapp.feature_auth.presentation.fragment.forget_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class ForgetPasswordViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ForgetPasswordUIState>()
    val uiState: LiveData<ForgetPasswordUIState> = _uiState

    private val _interactions = MutableLiveData<Event<ForgetPasswordActions>>()
    val interactions: LiveData<Event<ForgetPasswordActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(ForgetPasswordUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class ForgetPasswordActions {
    data class Navigate(val destination: NavDirections) : ForgetPasswordActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class ForgetPasswordUIState {

    object Loading : ForgetPasswordUIState()

    data class Error(val message: String = "Try again") : ForgetPasswordUIState()

    data class LimitError(val message: String) : ForgetPasswordUIState()

    data class Message(val message: String): ForgetPasswordUIState()
}

