package com.chacha.theapp.core.presentation.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction


class ViewPagerAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

    // declare arrayList to contain fragments and its title
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getCount() = mFragmentList.size
    override fun getPageTitle(position: Int) =mFragmentTitleList[position]
    override fun getItem(position: Int) = mFragmentList[position]

    fun addFragment(fragment: Fragment, title: String) {
        // add each fragment and its title to the array list
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}
