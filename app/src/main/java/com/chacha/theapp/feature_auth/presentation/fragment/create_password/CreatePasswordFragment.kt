package com.chacha.theapp.feature_auth.presentation.fragment.create_password

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.chacha.theapp.R
import com.chacha.theapp.databinding.CreatePasswordFragmentBinding
import com.chacha.theapp.databinding.IncomeFragmentBinding
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeViewModel
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePasswordFragment : Fragment(R.layout.create_password_fragment) {
    private val viewModel: CreatePasswordViewModel by viewModel()
    private val binding by viewBinding(CreatePasswordFragmentBinding::bind)
    private val args: CreatePasswordFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

        createAccount()



    }

    private fun createAccount() {

        binding.apply {
            confirmBtn.setOnClickListener {
                when{
                    passwordPhoneTextInputEditText.text.toString().trim().isEmpty()->{
                        Toast.makeText(
                            requireContext(), "Enter your Password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    confirmPasswordTextInputEditText.text.toString().trim().isEmpty() ->{
                        Toast.makeText(
                            requireContext(), "Confirm YourPassword",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    passwordPhoneTextInputEditText.text.toString().trim() != confirmPasswordTextInputEditText.text.toString().trim() ->{
                        Toast.makeText(
                            requireContext(), " Your Password Doesn't Match",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    else ->{
                        viewModel.createAccount(
                            email = args.email,
                            phoneNumber = args.phoneNumber,
                            idNumber = args.idNumber,
                            password = passwordPhoneTextInputEditText.text.toString().trim()
                        )
                    }

                }
            }
        }
    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                CreatePasswordUIState.Loading -> {}  //renderLoading()
                is CreatePasswordUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is CreatePasswordActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }

}