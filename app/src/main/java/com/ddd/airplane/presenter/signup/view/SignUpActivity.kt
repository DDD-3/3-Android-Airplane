package com.ddd.airplane.presenter.signup.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.provider.ContextProvider
import com.ddd.airplane.databinding.SignupActivityBinding
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.presenter.signup.viewmodel.SignUpViewModel

/**
 * 회원가입
 * @author jess
 */
class SignUpActivity : BaseActivity<SignupActivityBinding, SignUpViewModel>() {

    override val layoutRes = R.layout.signup_activity
    override val viewModelClass = SignUpViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
