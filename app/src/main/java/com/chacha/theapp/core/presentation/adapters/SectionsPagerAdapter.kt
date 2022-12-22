package com.chacha.theapp.core.presentation.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.chacha.theapp.R
import com.chacha.theapp.feature_accounts.presentation.fragment.expense.ExpenseFragment
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeFragment
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementFragment

private val TAB_TITLES = arrayOf(
    R.string.statements,
    R.string.income,
    R.string.expense
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> StatementFragment()
            1 -> IncomeFragment()
            2 -> ExpenseFragment()
            else -> throw IllegalStateException("Invalid tab position")
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}