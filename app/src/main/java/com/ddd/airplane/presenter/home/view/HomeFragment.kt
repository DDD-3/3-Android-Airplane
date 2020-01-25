package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.views.decoration.DividerItemSpace
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
    private val homeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HomeAdapter(context)
    }

    override fun initDataBinding() {
        super.initDataBinding()
        viewModel.homeList.observe(this, Observer {
            if (homeAdapter.itemCount < 1) {
                homeAdapter.addAllItem(it)
            }
        })
    }

    override fun initLayout() {
        rv_home.run {
            addItemDecoration(DividerItemSpace(space = context.resources.getDimensionPixelSize(R.dimen.dp40)))
            adapter = homeAdapter
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.getHomeList()
    }
}
