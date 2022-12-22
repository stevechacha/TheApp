package com.chacha.theapp.core.presentation.fragment.more

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class MoreViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _uiState = MutableLiveData<MoreUIState>()
    val uiState: LiveData<MoreUIState> = _uiState

    private val _interactions = MutableLiveData<Event<MoreActions>>()
    val interactions: LiveData<Event<MoreActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(MoreUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class MoreActions {
    data class Navigate(val destination: NavDirections) : MoreActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class MoreUIState {

    object Loading : MoreUIState()

    data class Error(val message: String = "Try again") : MoreUIState()

    data class LimitError(val message: String) : MoreUIState()

    data class Message(val message: String): MoreUIState()
}
