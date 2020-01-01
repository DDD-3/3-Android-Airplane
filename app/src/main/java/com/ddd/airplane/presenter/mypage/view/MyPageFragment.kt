package com.ddd.airplane.presenter.mypage.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.databinding.MypageFragmentBinding
import com.ddd.airplane.presenter.base.BaseFragment
import com.ddd.airplane.presenter.signin.view.SignInActivity
import kotlinx.android.synthetic.main.mypage_fragment.*

/**
 * 마이페이지
 * @author jess
 */
class MyPageFragment : BaseFragment<MypageFragmentBinding>(), View.OnClickListener {

    override fun setLayoutId() = R.layout.mypage_fragment

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@MyPageFragment
        }
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
                startActivity(Intent(context, SignInActivity::class.java))
            }
        }
    }
}
