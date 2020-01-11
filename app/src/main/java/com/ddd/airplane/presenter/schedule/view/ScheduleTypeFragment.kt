package com.ddd.airplane.presenter.schedule.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.databinding.ScheduleMainFragmentBinding
import com.ddd.airplane.databinding.ScheduleTypeFragmentBinding
import com.ddd.airplane.presenter.schedule.viewmodel.ScheduleMainViewModel

/**
 * 편성표
 * 2 Depth
 * @author jess
 */
class ScheduleTypeFragment : BaseFragment<ScheduleTypeFragmentBinding, ScheduleMainViewModel>() {

    override val layoutRes = R.layout.schedule_type_fragment
    override val viewModelClass = ScheduleMainViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}
