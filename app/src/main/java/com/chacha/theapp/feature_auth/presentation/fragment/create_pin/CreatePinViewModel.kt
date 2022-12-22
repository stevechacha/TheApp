package com.chacha.theapp.feature_auth.presentation.fragment.create_pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class CreatePinViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _uiState = MutableLiveData<CreatePinUIState>()
    val uiState: LiveData<CreatePinUIState> = _uiState

    private val _interactions = MutableLiveData<Event<CreatePinActions>>()
    val interactions: LiveData<Event<CreatePinActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(CreatePinUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class CreatePinActions {
    data class Navigate(val destination: NavDirections) : CreatePinActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class CreatePinUIState {

    object Loading : CreatePinUIState()

    data class Error(val message: String = "Try again") : CreatePinUIState()

    data class LimitError(val message: String) : CreatePinUIState()

    data class Message(val message: String): CreatePinUIState()
}
