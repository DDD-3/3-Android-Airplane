package com.ddd.airplane.presenter.schedule.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.databinding.RecyclerViewFragmentBinding
import com.ddd.airplane.databinding.ScheduleMainFragmentBinding
import com.ddd.airplane.presenter.schedule.viewmodel.ScheduleMainViewModel

/**
 * 편성표
 * 3 Depth
 * @author jess
 */
class ScheduleItemFragment : BaseFragment<RecyclerViewFragmentBinding, ScheduleMainViewModel>() {

    override val layoutRes = R.layout.recycler_view_fragment
    override val viewModelClass = ScheduleMainViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}
