package com.ddd.airplane.presenter.main.view

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ddd.airplane.R
import com.ddd.airplane.common.extension.replace
import com.ddd.airplane.presenter.home.view.HomeFragment
import com.ddd.airplane.presenter.mypage.view.MyPageFragment
import com.ddd.airplane.presenter.schedule.view.ScheduleFragment


class BottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : com.google.android.material.bottomnavigation.BottomNavigationView(
    context,
    attrs,
    defStyleAttr
) {

    lateinit var fragmentManager: FragmentManager

//    private val homeFragment: Fragment by lazy {
//        HomeFragment()
//    }
//
//    private val scheduleFragment: Fragment by lazy {
//        ScheduleFragment()
//    }
//
//    private val myPageFragment: Fragment by lazy {
//        MyPageFragment()
//    }

    init {
//        setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.main_navi_item_schedule -> {
//                    // 편성표
//                    scheduleFragment.replace(fragmentManager, R.id.fl_main)
//                    true
//                }
//
//                R.id.main_navi_item_chat -> {
//                    // 채팅
//                    myPageFragment.replace(fragmentManager, R.id.fl_main)
//                    true
//                }
//
//                R.id.main_navi_item_search -> {
//                    // 검색
//                    myPageFragment.replace(fragmentManager, R.id.fl_main)
//                    true
//                }
//
//                R.id.main_navi_item_mypage -> {
//                    // 마이페이지
//                    myPageFragment.replace(fragmentManager, R.id.fl_main)
//                    true
//                }
//
//                else -> {
//                    // 홈
//                    homeFragment.replace(fragmentManager, R.id.fl_main)
//                    true
//                }
//            }
//        }
    }

    fun init() {
        // 첫 화면
//        homeFragment.replace(fragmentManager, R.id.fl_main)
    }

}