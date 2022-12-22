package com.chacha.theapp.feature_auth.presentation.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.chacha.theapp.core.data.network.TheAppApi
import com.chacha.theapp.core.util.Event
import com.chacha.theapp.core.util.asEvent
import com.chacha.theapp.core.util.handleThrowable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LoginViewModel(private val api: TheAppApi) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUIState>()
    val uiState: LiveData<LoginUIState> = _uiState

    private val _interactions = MutableLiveData<Event<LoginActions>>()
    val interactions: LiveData<Event<LoginActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(LoginUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }
    fun loginUser(
        email: String? = "",
        phoneNumber: String? = "",
        password: String
    ){
        _uiState.postValue(LoginUIState.Loading)
        viewModelScope.launch (exceptionHandler){
            val response = withContext(Dispatchers.IO){
                api.login(
                    TheAppApi.Login(
                        Email = email,
                        Password = password,
                        PhoneNumber = phoneNumber
                    )
                )
            }
            if(response.code() == 200){
                _interactions.postValue(
                    LoginActions.Navigate(
                        LoginFragmentDirections.loginFragmentToHomeFragment()
                    ).asEvent())
            } else if (response.code() ==403){
                _uiState.postValue(LoginUIState.Error(response.message()))
            }
        }

    }

    fun navigateToRegister() {
       /* _interactions.postValue(
            LoginActions.Navigate(
                LoginFragmentDirections.loginFragmentToRegisterFragment()
            ).asEvent()
        )*/
        val toRegister = LoginFragmentDirections.loginFragmentToRegisterFragment()
        val action = LoginActions.Navigate(toRegister)
        _interactions.postValue(action.asEvent())
    }
}

sealed class LoginActions {
    data class Navigate(val destination: NavDirections) : LoginActions()
    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): LoginActions()
}

sealed class LoginUIState {

    object Loading : LoginUIState()

    data class Error(val message: String = "Try again") : LoginUIState()

    data class LimitError(val message: String) : LoginUIState()

    data class Message(val message: String): LoginUIState()
}