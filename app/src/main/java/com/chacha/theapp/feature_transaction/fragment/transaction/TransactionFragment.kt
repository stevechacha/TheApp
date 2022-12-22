package com.chacha.theapp.feature_transaction.fragment.transaction

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.TransactionFragmentBinding
import com.chacha.theapp.feature_auth.presentation.fragment.register.RegisterActions
import com.chacha.theapp.feature_auth.presentation.fragment.register.RegisterUIState
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionFragment : Fragment(R.layout.transaction_fragment) {
    private val binding by viewBinding(TransactionFragmentBinding::bind)
    private val viewModel: TransactionViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                TransactionUIState.Loading ->{
                    //renderLoading()

                }
                is TransactionUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                is TransactionUIState.LimitError ->{

                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is TransactionActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }



}