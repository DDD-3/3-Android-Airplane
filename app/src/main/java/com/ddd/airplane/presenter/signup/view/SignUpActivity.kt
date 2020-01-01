package com.ddd.airplane.presenter.signup.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.SignupActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity

/**
 * 회원가입
 * @author jess
 */
class SignUpActivity : BaseActivity<SignupActivityBinding>() {
    override fun setLayoutId() = R.layout.signup_activity

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@SignUpActivity
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
