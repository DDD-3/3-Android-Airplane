package com.ddd.airplane.common.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

/**
 * Base ViewPager Adapter
 */
abstract class BaseViewPagerAdapter(fragmentActivity: FragmentActivity) :

    FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<Fragment>()

    public fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount() = fragments.size

    fun getItem(position: Int): Fragment {
        return fragments[position]
    }

}