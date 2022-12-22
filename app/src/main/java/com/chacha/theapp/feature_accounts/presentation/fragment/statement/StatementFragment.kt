package com.chacha.theapp.feature_accounts.presentation.fragment.statement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.StatementFragmentBinding
import com.chacha.theapp.feature_transaction.fragment.transaction.TransactionActions
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatementFragment : Fragment(R.layout.statement_fragment) {
    private val binding by viewBinding(StatementFragmentBinding::bind)
    private val viewModel: StatementViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()



    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                StatementUIState.Loading ->{
                    //renderLoading()
                }
                is StatementUIState.Error ->{
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else ->{}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is StatementActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }


}