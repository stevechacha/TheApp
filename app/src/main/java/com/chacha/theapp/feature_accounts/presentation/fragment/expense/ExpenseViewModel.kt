package com.chacha.theapp.feature_accounts.presentation.fragment.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class ExpenseViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ExpenseUIState>()
    val uiState: LiveData<ExpenseUIState> = _uiState

    private val _interactions = MutableLiveData<Event<ExpenseActions>>()
    val interactions: LiveData<Event<ExpenseActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(ExpenseUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class ExpenseActions {
    data class Navigate(val destination: NavDirections) : ExpenseActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class ExpenseUIState {

    object Loading : ExpenseUIState()

    data class Error(val message: String = "Try again") : ExpenseUIState()

    data class LimitError(val message: String) : ExpenseUIState()

    data class Message(val message: String): ExpenseUIState()
}
