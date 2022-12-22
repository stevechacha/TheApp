package com.chacha.theapp.core.presentation.fragment.pinlock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class PinLockViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _uiState = MutableLiveData<PinLockUIState>()
    val uiState: LiveData<PinLockUIState> = _uiState

    private val _interactions = MutableLiveData<Event<PinLockActions>>()
    val interactions: LiveData<Event<PinLockActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(PinLockUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class PinLockActions {
    data class Navigate(val destination: NavDirections) : PinLockActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class PinLockUIState {

    object Loading : PinLockUIState()

    data class Error(val message: String = "Try again") : PinLockUIState()

    data class LimitError(val message: String) : PinLockUIState()

    data class Message(val message: String): PinLockUIState()
}

