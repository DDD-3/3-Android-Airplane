package com.ddd.airplane.presenter.schedule.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.views.DividerItemSpace
import com.ddd.airplane.data.response.ScheduleData
import com.ddd.airplane.databinding.ScheduleFragmentBinding
import com.ddd.airplane.databinding.ScheduleHeaderItemBinding
import com.ddd.airplane.databinding.ScheduleTypeItemBinding
import com.ddd.airplane.presenter.schedule.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.schedule_fragment.*
import kotlinx.android.synthetic.main.schedule_header.*
import kotlinx.android.synthetic.main.schedule_type_item.view.*

/**
 * 편성표
 * 1 Depth
 * @author jess
 */
class ScheduleFragment : BaseFragment<ScheduleFragmentBinding, ScheduleViewModel>() {

    override val layoutRes = R.layout.schedule_fragment
    override val viewModelClass = ScheduleViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()
        viewModel.run {

        }
    }

    override fun initLayout() {

        vp_schedule_main.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.setCurrentPage(position)
            }
        })

        // header
        rv_header_item.apply {
            addItemDecoration( // 간격
                DividerItemSpace(
                    DividerItemSpace.HORIZONTAL,
                    context.resources.getDimensionPixelSize(R.dimen.dp8)
                )
            )

            adapter = object :

                BaseRecyclerViewAdapter<ScheduleData, ScheduleHeaderItemBinding>(R.layout.schedule_header_item) {

                override fun onBindData(
                    position: Int,
                    model: ScheduleData,
                    dataBinding: ScheduleHeaderItemBinding
                ) {
                    dataBinding.let {
                        it.model = model
                        it.position = position

                        // 현재 포지션
                        viewModel.position.observe(this@ScheduleFragment, Observer { position ->
                            it.current = position
                        })
                    }
                }
            }
        }

        set1DepthViewPager()
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    /**
     * 1 Depth ViewPager
     *
     * 방송사별, 주제별
     */
    private fun set1DepthViewPager() {
        // 1 depth pager
        vp_schedule_main.apply {
            adapter = object :

                BaseRecyclerViewAdapter<ScheduleData, ScheduleTypeItemBinding>(R.layout.schedule_type_item) {

                override fun onBindData(
                    position: Int,
                    model: ScheduleData,
                    dataBinding: ScheduleTypeItemBinding
                ) {
                    val view = dataBinding.root

                    // 주제별 viewPager 삽입
                    set2DepthViewPager(view.vp_schedule_type)
                }
            }
        }
    }

    /**
     * 2 Depth ViewPager
     *
     * 방송사별 : SBS, KBS, MBC ...
     * 주제별 : 종편/뉴스, 연예오락, 영화/시리즈 ...
     *
     * @param view
     */
    private fun set2DepthViewPager(view: ViewPager2) {

    }
}
