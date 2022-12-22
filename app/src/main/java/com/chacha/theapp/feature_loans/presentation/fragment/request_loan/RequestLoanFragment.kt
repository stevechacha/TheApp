package com.chacha.theapp.feature_loans.presentation.fragment.request_loan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.RequestLoanFragmentBinding
import com.chacha.theapp.feature_auth.presentation.fragment.reset_pin.ResetPinActions
import com.chacha.theapp.feature_auth.presentation.fragment.reset_pin.ResetPinUIState
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RequestLoanFragment : Fragment() {
    private val binding by viewBinding (RequestLoanFragmentBinding::bind)
    private val viewModel: RequestLoanViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                RequestLoanUIState.Loading ->{}  //renderLoading()
                is RequestLoanUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is RequestLoanActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }


}