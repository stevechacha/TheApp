package com.chacha.theapp.feature_accounts.presentation.fragment.account

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.chacha.theapp.R
import com.chacha.theapp.databinding.AccountFragmentBinding
import com.chacha.theapp.core.presentation.adapters.MyFragmentPagerAdapter
import com.chacha.theapp.core.presentation.adapters.SectionsPagerAdapter
import com.chacha.theapp.core.presentation.adapters.ViewPagerAdapter
import com.chacha.theapp.feature_accounts.presentation.fragment.expense.ExpenseFragment
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeFragment
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementFragment
import com.chacha.theapp.core.util.observeEvent
import com.chacha.theapp.core.util.viewBinding
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountFragment() : Fragment(R.layout.account_fragment) {
    private val binding by viewBinding(AccountFragmentBinding::bind)
    private val viewModel: AccountViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        setUpObservers()


    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                AccountUIState.Loading -> {
                    //renderLoading()
                }
                is AccountUIState.Error -> {
                    //renderError()
//                    renderError(it.message)
                }
                is AccountUIState.Message ->{
//                    renderSuccess(it.message)
                }
                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is AccountActions.Navigate -> findNavController().navigate(it.destination)
//                is AccountActions.BottomNavigate -> showDialog(it.bottomSheetDialogFragment)
            }
        }
    }

}