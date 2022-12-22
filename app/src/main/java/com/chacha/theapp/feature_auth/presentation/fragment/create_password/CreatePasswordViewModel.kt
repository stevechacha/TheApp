package com.chacha.theapp.feature_auth.presentation.fragment.create_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.chacha.theapp.core.data.network.TheAppApi
import com.chacha.theapp.core.presentation.fragment.home.HomeActions
import com.chacha.theapp.core.presentation.fragment.home.HomeUIState
import com.chacha.theapp.feature_auth.presentation.fragment.register.RegisterActions
import com.chacha.theapp.presentation.fragment.register.RegisterFragmentDirections
import com.chacha.theapp.feature_auth.presentation.fragment.register.RegisterUIState
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.asEvent
import com.chacha.theapp.core.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class CreatePasswordViewModel (private val api: TheAppApi): ViewModel() {

    private val _uiState = MutableLiveData<CreatePasswordUIState>()
    val uiState: LiveData<CreatePasswordUIState> = _uiState

    private val _interactions = MutableLiveData<Event<CreatePasswordActions>>()
    val interactions: LiveData<Event<CreatePasswordActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(CreatePasswordUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

    fun createAccount(
        email: String,
        phoneNumber: String,
        idNumber: String,
        password: String
    ) {
        _uiState.postValue(CreatePasswordUIState.Loading)
        viewModelScope.launch(exceptionHandler) {
            val userDataResponse = withContext(Dispatchers.Main) {
                api.registerUser(
                    Email = email,
                    PhoneNumber = phoneNumber,
                    IDNumber = idNumber,
                    Password = password
                )
            }
            if (userDataResponse.isSuccessful) {
                val toPermission = CreatePasswordFragmentDirections.createPasswordFragmentToHomeFragment()
                val actions: CreatePasswordActions = CreatePasswordActions.Navigate(toPermission)
                _interactions.postValue(actions.asEvent())

            } else if (userDataResponse.isSuccessful){
                _uiState.postValue(CreatePasswordUIState.Error(message = "Try again"))

            }
        }
    }

}
sealed class CreatePasswordActions {
    data class Navigate(val destination: NavDirections) : CreatePasswordActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): AccountActions()
}

sealed class CreatePasswordUIState {

    object Loading : CreatePasswordUIState()

    data class Error(val message: String = "Try again") : CreatePasswordUIState()

    data class LimitError(val message: String) : CreatePasswordUIState()

    data class Message(val message: String): CreatePasswordUIState()
}
