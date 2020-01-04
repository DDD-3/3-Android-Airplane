package com.ddd.airplane.presenter.schedule.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ScheduleFragmentBinding
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.presenter.schedule.viewmodel.ScheduleViewModel

/**
 * 편성표
 * @author jess
 */
class ScheduleFragment : BaseFragment<ScheduleFragmentBinding, ScheduleViewModel>() {

    override val layoutRes = R.layout.schedule_fragment
    override val viewModelClass = ScheduleViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}
