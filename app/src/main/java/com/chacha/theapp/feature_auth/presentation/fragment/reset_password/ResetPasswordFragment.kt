package com.chacha.theapp.feature_auth.presentation.fragment.reset_password

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.ResetPasswordFragmentBinding
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementActions
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordFragment : Fragment(R.layout.reset_pin_fragment) {
    private val binding by viewBinding(ResetPasswordFragmentBinding::bind)
    private val viewModel : ResetPasswordViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                ResetPasswordUIState.Loading -> {
                    //renderLoading()

                }

                is ResetPasswordUIState.Error -> {

                }
                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is ResetPasswordActions.Navigate -> findNavController().navigate(it.destination)
            }

        }
    }

}