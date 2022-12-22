package com.chacha.theapp.core.presentation.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.HomeFragmentBinding
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel



/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class HomeFragment : Fragment(R.layout.home_fragment) {
    private val binding by viewBinding (HomeFragmentBinding::bind)
    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                HomeUIState.Loading -> {
                    // renderLoading
                }
               is HomeUIState.Error -> {
                   // renderError
                   // renderError(it.message)
                }
                is HomeUIState.Message ->{
                    // renderSuccess(it.message)
                }
                else ->{}
            }
        }
        viewModel.interaction.observeEvent(viewLifecycleOwner){
            when(it){
                is HomeActions.Navigate -> findNavController().navigate(it.destination)
            }
        }

    }

}