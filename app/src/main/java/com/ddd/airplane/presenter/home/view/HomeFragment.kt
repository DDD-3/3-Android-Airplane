package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.utils.DeviceUtils
import com.ddd.airplane.databinding.HomeFragmentBinding
import com.ddd.airplane.presenter.home.viewmodel.HomeViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.home_fragment.*
import timber.log.Timber
import kotlin.math.abs


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
        abl_home.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                setStatusBarColor(false)
            } else {
                setStatusBarColor(true)
            }
        })
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        setStatusBarColor(true)
    }

    private fun setStatusBarColor(isExpended: Boolean) {
        val color = if (isExpended) {
            R.color.color_0cbfff
        } else {
            R.color.brand_black
        }
        DeviceUtils.setStatusBarColor(activity, color)
    }

}
