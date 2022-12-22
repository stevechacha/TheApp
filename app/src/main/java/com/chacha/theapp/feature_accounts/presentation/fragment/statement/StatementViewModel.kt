package com.chacha.theapp.feature_accounts.presentation.fragment.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event

class StatementViewModel : ViewModel() {

    private val _uiState = MutableLiveData<StatementUIState>()
    val uiState: LiveData<StatementUIState> = _uiState

    private val _interactions = MutableLiveData<Event<StatementActions>>()
    val interactions : LiveData<Event<StatementActions>> = _interactions


}

sealed class StatementActions{
    data class Navigate( val destination: NavDirections): StatementActions()
}

sealed class StatementUIState{
    object Loading: StatementUIState()

    data class LimitError(val title: String): StatementUIState()

    data class Error(val title: String = "Try again", val message: Any) : StatementUIState()
}

