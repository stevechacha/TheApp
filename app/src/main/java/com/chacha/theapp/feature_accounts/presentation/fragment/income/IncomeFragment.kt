package com.chacha.theapp.feature_accounts.presentation.fragment.income

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.IncomeFragmentBinding
import com.chacha.theapp.databinding.StatementFragmentBinding
import com.chacha.theapp.feature_accounts.presentation.fragment.account.AccountViewModel
import com.chacha.theapp.feature_loans.presentation.fragment.request_loan.RequestLoanViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementViewModel
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class IncomeFragment : Fragment(R.layout.income_fragment) {
    private val viewModel: IncomeViewModel by viewModel()
    private val binding by viewBinding(IncomeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                IncomeUIState.Loading ->{}  //renderLoading()
                is IncomeUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is IncomeActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }





}