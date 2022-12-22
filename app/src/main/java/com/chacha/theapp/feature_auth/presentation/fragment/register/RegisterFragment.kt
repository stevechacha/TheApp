package com.chacha.theapp.feature_auth.presentation.fragment.register

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.RegisterFragmentBinding
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import com.chacha.theapp.core.validators.EmailValidator
import com.chacha.theapp.core.validators.EmptyValidator
import com.chacha.theapp.core.validators.base.BaseValidator
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment(R.layout.register_fragment) {
    private val binding by viewBinding(RegisterFragmentBinding::bind)
    private val viewModel: RegisterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpObservers()
        navigateToLogin()
        validateUser()

    }

    private fun navigateToLogin() {
        binding.apply {
            signUpBtn.setOnClickListener {
                viewModel.navigateToLogin()
            }
        }
    }

    private fun validateUser (){
        binding.apply {
            signUpBtn.setOnClickListener {
                when{
                    emailPhoneTextInputEditText.text.toString().trim().isEmpty() ->{
                        Toast.makeText(
                            requireContext(), "Enter Email",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    idNumberTextInputEditText.text.toString().trim().isEmpty() ->{
                        Toast.makeText(
                            requireContext(), "Enter Id Number",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    mobileNumberTextInputEditText.text.toString().trim().isEmpty() ->{
                        Toast.makeText(
                            requireContext(), "Enter your Phone Number",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else ->{
                        viewModel.navigateToCreatePassword(
                            email =emailPhoneTextInputEditText.text.toString().trim(),
                            phoneNumber = mobileNumberTextInputEditText.text.toString().trim(),
                            idNumber = idNumberTextInputEditText.text.toString().trim(),
                        )
//                        loading.isVisible = true
                        onRegisterFinished()
                    }
                }
            }
        }
    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                RegisterUIState.Loading ->{}  //renderLoading()
                is RegisterUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is RegisterActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }

    private fun onRegisterFinished() {
        val sharedPref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()

    }
}