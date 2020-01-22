package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.base.BaseViewHolder
import com.ddd.airplane.common.consts.Home
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.databinding.HomeFragmentBinding
import com.ddd.airplane.presenter.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

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

        // 홈 배너 리스트
        // viewPager
        rv_home.adapter =

            object : BaseRecyclerViewAdapter<HomeData, ViewDataBinding>() {

                override fun getItemViewType(position: Int): Int {
                    return viewModel.getItemViewType(position)
                }

                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): BaseViewHolder<HomeData> {

                    val dataBinding = when (Home.Type.values()[viewType]) {
                        Home.Type.SWIPE_BANNER -> {
                            createViewDataBinding(parent, R.layout.home_swipe_banner)
                        }

                        else -> {
                            createViewDataBinding(parent, R.layout.empty_view)
                        }
                    }
                    return createViewHolder(dataBinding)
                }
            }

    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.getHomeList()
    }
}
