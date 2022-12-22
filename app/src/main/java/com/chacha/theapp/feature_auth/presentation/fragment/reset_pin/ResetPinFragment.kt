package com.chacha.theapp.feature_auth.presentation.fragment.reset_pin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.ResetPinFragmentBinding
import com.chacha.theapp.feature_auth.presentation.fragment.register.RegisterActions
import com.chacha.theapp.feature_auth.presentation.fragment.register.RegisterUIState
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ResetPinFragment : Fragment(R.layout.reset_pin_fragment) {
    private val binding by viewBinding(ResetPinFragmentBinding::bind)
    private val viewModel: ResetPinViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                ResetPinUIState.Loading ->{}  //renderLoading()
                is ResetPinUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is ResetPinActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }


}