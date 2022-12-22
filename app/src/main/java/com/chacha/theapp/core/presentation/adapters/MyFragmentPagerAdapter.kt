package com.chacha.theapp.core.presentation.adapters


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.chacha.theapp.R
import com.chacha.theapp.feature_accounts.presentation.fragment.expense.ExpenseFragment
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeFragment
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementFragment

class MyFragmentPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
    ) : FragmentPagerAdapter(fragmentManager) {

    private val tabTitle = arrayOf(
        R.string.statements,
        R.string.income,
        R.string.expense)


    // This determines the fragment for each tab
     override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> StatementFragment()
            1 -> IncomeFragment()
            2 -> ExpenseFragment()
            else -> throw IllegalStateException("Invalid tab position")
        }
    }
    // This determines the number of tabs
    override fun getCount(): Int =3

    // This determines the title for each tab
    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return context.resources.getString(tabTitle[position])
    }

}
