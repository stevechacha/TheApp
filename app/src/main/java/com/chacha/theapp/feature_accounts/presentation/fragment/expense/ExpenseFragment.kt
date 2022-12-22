package com.chacha.theapp.feature_accounts.presentation.fragment.expense

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.ExpenseFragmentBinding
import com.chacha.theapp.databinding.StatementFragmentBinding
import com.chacha.theapp.feature_accounts.presentation.fragment.account.AccountViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementViewModel
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExpenseFragment : Fragment(R.layout.expense_fragment) {
    private val viewModel: ExpenseViewModel by viewModel()
    private val binding by viewBinding(ExpenseFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpObservers()



    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                ExpenseUIState.Loading ->{}  //renderLoading()
                is ExpenseUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is ExpenseActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }




}