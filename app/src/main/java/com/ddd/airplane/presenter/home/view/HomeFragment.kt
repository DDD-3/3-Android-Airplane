package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.base.BaseViewHolder
import com.ddd.airplane.common.consts.Home
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.databinding.HomeFragmentBinding
import com.ddd.airplane.presenter.home.view.adapter.HomeAdapter
import com.ddd.airplane.presenter.home.view.viewholder.SwipeViewHolder
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

        rv_home.adapter =

            object : BaseRecyclerViewAdapter<HomeData.ItemData<Any>, ViewDataBinding>() {

                override fun getItemViewType(position: Int): Int {
                    return viewModel.getStyle(position).ordinal
                }

                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): BaseViewHolder<HomeData.ItemData<Any>> {

                    val style = getStyle(viewType)
                    val layoutId = when (style) {
                        Home.Style.SWIPE_BANNER -> R.layout.home_swipe_banner
                        else -> {
                            R.layout.empty_view
                        }
                    }

                    val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(
                        LayoutInflater.from(parent.context),
                        layoutId,
                        parent,
                        false
                    )

                    return ViewHolder(dataBinding, style)
                }


                override fun onBindViewHolder(
                    baseHolder: BaseViewHolder<HomeData.ItemData<Any>>,
                    position: Int
                ) {
                    tryCatch {
                        val item = getItems()[position]
                        val holder = (baseHolder as ViewHolder)
                        when (getStyle(getItemViewType(position))) {
                            Home.Style.SWIPE_BANNER -> {
                                holder.swipe?.onBind(item)
                            }

                            else -> {

                            }
                        }
                    }
                }

                private fun getStyle(viewType: Int) = Home.Style.values()[viewType]

                /**
                 * View Holder
                 */
                inner class ViewHolder(
                    binding: ViewDataBinding,
                    style: Home.Style
                ) : BaseViewHolder<HomeData.ItemData<Any>>(binding) {

                    var swipe: SwipeViewHolder? = null

                    init {
                        when (style) {
                            Home.Style.SWIPE_BANNER -> {
                                swipe = SwipeViewHolder(context, binding)
                            }

                            else -> {

                            }
                        }
                    }
                }

            }
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.getHomeList()
    }
}
