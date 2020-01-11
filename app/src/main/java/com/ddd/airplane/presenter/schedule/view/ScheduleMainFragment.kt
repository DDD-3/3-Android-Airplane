package com.ddd.airplane.presenter.schedule.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BaseViewPagerAdapter
import com.ddd.airplane.databinding.ScheduleMainFragmentBinding
import com.ddd.airplane.presenter.schedule.viewmodel.ScheduleMainViewModel
import kotlinx.android.synthetic.main.schedule_main_fragment.*

/**
 * 편성표
 * 1 Depth
 * @author jess
 */
class ScheduleMainFragment : BaseFragment<ScheduleMainFragmentBinding, ScheduleMainViewModel>() {

    override val layoutRes = R.layout.schedule_main_fragment
    override val viewModelClass = ScheduleMainViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()
        viewModel.run {
            category.observe(this@ScheduleMainFragment, Observer {
                setCategoryPage(it)
            })
        }
    }

    override fun initLayout() {
        vp_schedule_main.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.setCurrentPage(position)
            }
        })
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    /**
     * 화면구성
     */
    private fun setCategoryPage(bundleList: ArrayList<Bundle>) {
        val adapter = BaseViewPagerAdapter(this)
        bundleList.forEach {
            val fragment = ScheduleTypeFragment().apply {
                arguments = it
            }
            adapter.addFragment(fragment)
        }

        vp_schedule_main.adapter = adapter
    }
}
