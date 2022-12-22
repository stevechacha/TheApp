package com.chacha.theapp.feature_loans.presentation.fragment.request_loan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class RequestLoanViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _uiState = MutableLiveData<RequestLoanUIState>()
    val uiState: LiveData<RequestLoanUIState> = _uiState

    private val _interactions = MutableLiveData<Event<RequestLoanActions>>()
    val interactions: LiveData<Event<RequestLoanActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(RequestLoanUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class RequestLoanActions {
    data class Navigate(val destination: NavDirections) : RequestLoanActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class RequestLoanUIState {

    object Loading : RequestLoanUIState()

    data class Error(val message: String = "Try again") : RequestLoanUIState()

    data class LimitError(val message: String) : RequestLoanUIState()

    data class Message(val message: String): RequestLoanUIState()

}