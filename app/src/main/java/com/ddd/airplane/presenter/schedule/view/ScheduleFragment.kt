package com.ddd.airplane.presenter.schedule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ScheduleFragmentBinding
import com.ddd.airplane.presenter.base.view.BaseFragment

/**
 * 편성표
 * @author jess
 */
class ScheduleFragment : BaseFragment<ScheduleFragmentBinding>() {

    override fun getLayoutId() = R.layout.schedule_fragment

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@ScheduleFragment
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}
