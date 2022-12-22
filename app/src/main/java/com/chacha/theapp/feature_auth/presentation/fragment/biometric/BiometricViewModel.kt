package com.chacha.theapp.feature_auth.presentation.fragment.biometric

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class BiometricViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _uiState = MutableLiveData<BiometricUIState>()
    val uiState: LiveData<BiometricUIState> = _uiState

    private val _interactions = MutableLiveData<Event<BiometricActions>>()
    val interactions: LiveData<Event<BiometricActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(BiometricUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class BiometricActions {
    data class Navigate(val destination: NavDirections) : BiometricActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class BiometricUIState {

    object Loading : BiometricUIState()

    data class Error(val message: String = "Try again") : BiometricUIState()

    data class LimitError(val message: String) : BiometricUIState()

    data class Message(val message: String): BiometricUIState()
}

