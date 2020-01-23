package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.databinding.HomeFragmentBinding
import com.ddd.airplane.presenter.home.view.adapter.HomeAdapter
import com.ddd.airplane.presenter.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * 홈
 * @author jess
 */
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override val layoutRes = R.layout.home_fragment
    override val viewModelClass = HomeViewModel::class.java

    // 홈 어댑터
    private val adapter = HomeAdapter(context)

    override fun initDataBinding() {
        super.initDataBinding()
        viewModel.run {
            homeList.observe(this@HomeFragment, Observer {
                adapter.addAllItem(it)
            })
        }
    }

    override fun initLayout() {

        // 홈 배너 리스트
        rv_home.adapter = adapter

    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.getHomeList()
    }
}
