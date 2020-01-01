package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.HomeFragmentBinding
import com.ddd.airplane.presenter.base.BaseFragment

/**
 * 홈
 * @author jess
 */
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    override fun setLayoutId() = R.layout.home_fragment

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@HomeFragment
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }


}
