package com.chacha.theapp.feature_loans.presentation.fragment.pay_loan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class PayLoanViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _uiState = MutableLiveData<PayLoanUIState>()
    val uiState: LiveData<PayLoanUIState> = _uiState

    private val _interactions = MutableLiveData<Event<PayLoanActions>>()
    val interactions: LiveData<Event<PayLoanActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(PayLoanUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class PayLoanActions {
    data class Navigate(val destination: NavDirections) : PayLoanActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class PayLoanUIState {

    object Loading : PayLoanUIState()

    data class Error(val message: String = "Try again") : PayLoanUIState()

    data class LimitError(val message: String) : PayLoanUIState()

    data class Message(val message: String): PayLoanUIState()

}

