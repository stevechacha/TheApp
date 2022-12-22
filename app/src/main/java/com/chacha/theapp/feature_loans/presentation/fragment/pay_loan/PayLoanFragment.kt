package com.chacha.theapp.feature_loans.presentation.fragment.pay_loan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.PayLoanFragmentBinding
import com.chacha.theapp.databinding.PinlockFragmentBinding
import com.chacha.theapp.core.presentation.fragment.pinlock.PinLockViewModel
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PayLoanFragment : Fragment(R.layout.pay_loan_fragment) {
    private val binding by viewBinding (PayLoanFragmentBinding::bind)
    private val viewModel : PayLoanViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                PayLoanUIState.Loading ->{}  //renderLoading()
                is PayLoanUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is PayLoanActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }


}