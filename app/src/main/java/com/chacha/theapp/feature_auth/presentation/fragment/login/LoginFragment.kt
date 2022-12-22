package com.chacha.theapp.feature_auth.presentation.fragment.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.LoginFragmentBinding
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import com.chacha.theapp.core.validators.EmailValidator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.login_fragment) {

    private val viewModel: LoginViewModel by viewModel()
    private val binding by viewBinding(LoginFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        navigateToRegister()
        loginUser()

    }

    private fun loginUser() {
        binding.apply {
            toSignIn.setOnClickListener {
                when{
                    emailPhoneTextInputEditText.text.toString().trim().isEmpty() ->{
                        Toast.makeText(
                            requireContext(), "Enter your Email or Phone Number",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    passwordTextInputEditText.text.toString().trim().isEmpty() ->{
                        Toast.makeText(
                            requireContext(), "Enter Your Password",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    else -> {
                        viewModel.loginUser(
                            email= emailPhoneTextInputEditText.toString().trim(),
                            phoneNumber= emailPhoneTextInputEditText.toString().trim(),
                            password = passwordTextInputEditText.text.toString().trim()
                        )
                    }

                }
            }
        }
    }

    private fun navigateToRegister() {
        binding.apply {
            toSignUp.setOnClickListener {
                viewModel.navigateToRegister()
            }
        }
    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                LoginUIState.Loading -> {
                    //renderLoading()
                }
                is LoginUIState.Error -> {
                    //renderError()
                    renderError(it.message)
                }
                is LoginUIState.Message ->{
                    renderSuccess(it.message)
                }
                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is LoginActions.Navigate -> findNavController().navigate(it.destination)
                is LoginActions.BottomNavigate -> showDialog(it.bottomSheetDialogFragment)
            }
        }
    }

    private fun renderError(message: String) {
        binding.apply {
//            loading.isVisible = false
//            loginBtn.isEnable = true
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialog(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        bottomSheetDialogFragment.show(parentFragmentManager,bottomSheetDialogFragment.tag)

    }

    private fun renderSuccess(message: String) {
        binding.apply {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

}