package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.databinding.HomeFragmentBinding
import com.ddd.airplane.presenter.home.viewmodel.HomeViewModel

/**
 * 홈
 * @author jess
 */
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override val layoutRes = R.layout.home_fragment
    override val viewModelClass = HomeViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }


}
