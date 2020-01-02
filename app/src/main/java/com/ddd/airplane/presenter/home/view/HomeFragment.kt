package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.provider.ContextProvider
import com.ddd.airplane.databinding.HomeFragmentBinding
import com.ddd.airplane.presenter.base.BaseFragment
import com.ddd.airplane.presenter.home.viewmodel.HomeViewModel

/**
 * 홈
 * @author jess
 */
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    private var vm = HomeViewModel(ContextProvider(context))

    override fun getLayoutId() = R.layout.home_fragment

    override fun initDataBinding() {
        super.initDataBinding()
        dataBinding.vm = vm
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }


}
