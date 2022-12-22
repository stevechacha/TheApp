package com.chacha.theapp.feature_transaction.fragment.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import com.chacha.theapp.core.util.Event

class TransactionViewModel : ViewModel() {

    private val _uiState = MutableLiveData<TransactionUIState>()
    val uiState: LiveData<TransactionUIState> = _uiState

    private val _interactions = MutableLiveData<Event<TransactionActions>>()
    val interactions : LiveData<Event<TransactionActions>> = _interactions


}

sealed class TransactionActions{
    data class Navigate(val destination: NavDirections): TransactionActions()

}

sealed class TransactionUIState{
    object Loading: TransactionUIState()

    data class Error(val title: String): TransactionUIState()

    data class Message(val title: String, val message: Any): TransactionUIState()

    data class LimitError(val message: String) : TransactionUIState()


}