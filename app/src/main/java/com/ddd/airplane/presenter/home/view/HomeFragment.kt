package com.ddd.airplane.presenter.home.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.databinding.HomeFragmentBinding
import com.ddd.airplane.presenter.home.viewmodel.HomeViewModel


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

//        // bottomSheet
//        BottomSheetBehavior.from(fl_sheet).run {
//            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//
//                override fun onSlide(bottomSheet: View, slideOffset: Float) {
//
//                }
//
//                @SuppressLint("SwitchIntDef")
//                override fun onStateChanged(bottomSheet: View, newState: Int) {
//                    when (newState) {
//                        BottomSheetBehavior.STATE_EXPANDED -> {
//                            Timber.d("BottomSheetBehavior.STATE_EXPANDED")
//                            setExpandedColor(true)
//                        }
//                        BottomSheetBehavior.STATE_COLLAPSED -> {
//                            Timber.d("BottomSheetBehavior.STATE_COLLAPSED")
//                            setExpandedColor(false)
//                        }
//
//                    }
//                }
//            })
//        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {
//        setExpandedColor(false)
    }

    /**
     * Bottom Sheet 상태에 따라서 컬러 값 변경
     *
     * @param isExpanded
     */
    private fun setExpandedColor(isExpanded: Boolean) {
//        val color = if (isExpanded) {
//            R.color.brand_black
//        } else {
//            R.color.color_0cbfff
//        }
//        cdl_container.setBackgroundColor(ContextCompat.getColor(context!!, color))
//        DeviceUtils.setStatusBarColor(activity, color)
    }
}
