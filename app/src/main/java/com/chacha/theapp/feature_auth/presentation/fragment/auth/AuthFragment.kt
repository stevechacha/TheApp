package com.chacha.theapp.feature_auth.presentation.fragment.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.ActivityMainBinding.bind
import com.chacha.theapp.databinding.AuthFragmentBinding
import com.chacha.theapp.core.presentation.adapters.ViewPagerAdapter
import com.chacha.theapp.feature_accounts.presentation.fragment.expense.ExpenseFragment
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeFragment
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementFragment
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment(R.layout.auth_fragment) {
    private val binding by viewBinding(AuthFragmentBinding::bind)
    private val viewModel: AuthViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        onClick()



    }

    private fun onClick(){
        binding.apply {
            signInBtn.setOnClickListener {
                viewModel.navigateToLogin()
            }


            signUpBtn.setOnClickListener {
                viewModel.navigateToRegister()
            }
        }


    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                AuthUIState.Loading ->{}  //renderLoading()
                is AuthUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is AuthActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }



}