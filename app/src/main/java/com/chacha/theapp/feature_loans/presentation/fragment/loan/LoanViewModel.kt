package com.chacha.theapp.feature_loans.presentation.fragment.loan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class LoanViewModel : ViewModel() {

    private val _uiState = MutableLiveData<LoanUIState>()
    val uiState: LiveData<LoanUIState> = _uiState

    private val _interactions = MutableLiveData<Event<LoanActions>>()
    val interactions: LiveData<Event<LoanActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(LoanUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class LoanActions {
    data class Navigate(val destination: NavDirections) : LoanActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class LoanUIState {

    object Loading : LoanUIState()

    data class Error(val message: String = "Try again") : LoanUIState()

    data class LimitError(val message: String) : LoanUIState()

    data class Message(val message: String): LoanUIState()
}
