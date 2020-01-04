package com.ddd.airplane.presenter.signup.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.databinding.SignupActivityBinding
import com.ddd.airplane.presenter.signup.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.signup_activity.*

/**
 * 회원가입
 * @author jess
 */
class SignUpActivity : BaseActivity<SignupActivityBinding, SignUpViewModel>(),
    View.OnClickListener {

    override val layoutRes = R.layout.signup_activity
    override val viewModelClass = SignUpViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {
        val views = arrayOf(bt_sign_up)
        views.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_sign_up -> {
//                viewModel.reqSignUp(et_nick_name, et_email, et_password)
                viewModel.reqSignUp("jess01@test.com", "aaaaaa", "닉네임01")
            }
        }
    }
}
