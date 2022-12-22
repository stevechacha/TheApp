package com.chacha.theapp.feature_accounts.presentation.fragment.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.data.network.TheAppApi
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.handleThrowable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class AccountViewModel (private val api: TheAppApi) : ViewModel() {

    private val _uiState = MutableLiveData<AccountUIState>()
    val uiState: LiveData<AccountUIState> = _uiState

    private val _interactions = MutableLiveData<Event<AccountActions>>()
    val interactions: LiveData<Event<AccountActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(AccountUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}

sealed class AccountActions {
    data class Navigate(val destination: NavDirections) : AccountActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class AccountUIState {

    object Loading : AccountUIState()

    data class Error(val message: String = "Try again") : AccountUIState()

    data class LimitError(val message: String) : AccountUIState()

    data class Message(val message: String): AccountUIState()
}
