package com.ddd.airplane.presenter.main.view

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ddd.airplane.R
import com.ddd.airplane.common.extension.replace
import com.ddd.airplane.presenter.chat.list.view.ChatListFragment
import com.ddd.airplane.presenter.home.view.HomeFragment
import com.ddd.airplane.presenter.mypage.view.MyPageFragment
import com.ddd.airplane.presenter.schedule.view.ScheduleFragment
import com.ddd.airplane.presenter.search.view.SearchFragment

/**
 * 메인 하단 네비게이션 버튼
 *
 * @author jess
 * @param context
 * @param attrs
 * @param defStyleAttr
 */
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

    private val homeFragment = HomeFragment()

    private val scheduleFragment: Fragment by lazy {
        ScheduleFragment()
    }

    private val chatFragment: Fragment by lazy {
        ChatListFragment()
    }

    private val searchFragment: Fragment by lazy {
        SearchFragment()
    }

    private val myPageFragment: Fragment by lazy {
        MyPageFragment()
    }

    init {
        itemIconTintList = null
        setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_navi_item_schedule -> {
                    // 편성표
                    scheduleFragment.replace(fragmentManager, R.id.fl_main)
                    true
                }

                R.id.main_navi_item_chat -> {
                    // 채팅
                    chatFragment.replace(fragmentManager, R.id.fl_main)
                    true
                }

                R.id.main_navi_item_mypage -> {
                    // 마이페이지
                    myPageFragment.replace(fragmentManager, R.id.fl_main)
                    true
                }

                else -> {
                    // 홈
                    homeFragment.replace(fragmentManager, R.id.fl_main)
                    true
                }
            }
        }
    }

    fun init() {
        // 첫 화면
        homeFragment.replace(fragmentManager, R.id.fl_main)
    }

}