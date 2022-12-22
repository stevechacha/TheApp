package com.chacha.theapp.feature_accounts.presentation.fragment.income

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class IncomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _uiState = MutableLiveData<IncomeUIState>()
    val uiState: LiveData<IncomeUIState> = _uiState

    private val _interactions = MutableLiveData<Event<IncomeActions>>()
    val interactions: LiveData<Event<IncomeActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(IncomeUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}
sealed class IncomeActions {
    data class Navigate(val destination: NavDirections) : IncomeActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class IncomeUIState {

    object Loading : IncomeUIState()

    data class Error(val message: String = "Try again") : IncomeUIState()

    data class LimitError(val message: String) : IncomeUIState()

    data class Message(val message: String): IncomeUIState()
}

