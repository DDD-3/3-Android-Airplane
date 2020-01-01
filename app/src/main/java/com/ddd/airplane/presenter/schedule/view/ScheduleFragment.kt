package com.ddd.airplane.presenter.schedule.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ScheduleFragmentBinding
import com.ddd.airplane.presenter.base.BaseFragment

/**
 * 편성표
 * @author jess
 */
class ScheduleFragment : BaseFragment<ScheduleFragmentBinding>() {

    override fun setLayoutId() = R.layout.schedule_fragment

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
