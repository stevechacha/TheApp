package com.chacha.theapp.feature_auth.presentation.fragment.create_pin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.CreatePinFragmentBinding
import com.chacha.theapp.databinding.IncomeFragmentBinding
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeViewModel
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePinFragment : Fragment(R.layout.create_pin_fragment) {

    private val viewModel: CreatePinViewModel by viewModel()
    private val binding by viewBinding(CreatePinFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()



    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                CreatePinUIState.Loading ->{}  //renderLoading()
                is CreatePinUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is CreatePinActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }


}