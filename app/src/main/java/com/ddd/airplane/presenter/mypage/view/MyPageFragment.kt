package com.ddd.airplane.presenter.mypage.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.databinding.MypageFragmentBinding
import com.ddd.airplane.presenter.mypage.viewmodel.MyPageViewModel
import kotlinx.android.synthetic.main.mypage_fragment.*

/**
 * 마이페이지
 * @author jess
 */
class MyPageFragment : BaseFragment<MypageFragmentBinding, MyPageViewModel>(),
    View.OnClickListener {

    override val layoutRes = R.layout.mypage_fragment
    override val viewModelClass = MyPageViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {
        val views = arrayOf(cl_profile)
        views.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_profile -> { // 로그인화면 (임시)
                MemberManager.signIn(context) {

                }
            }
        }
    }
}
