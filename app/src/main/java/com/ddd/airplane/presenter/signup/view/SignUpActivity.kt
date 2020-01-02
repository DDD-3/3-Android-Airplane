package com.ddd.airplane.presenter.signup.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.provider.ContextProvider
import com.ddd.airplane.databinding.SignupActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity
import com.ddd.airplane.presenter.signup.viewmodel.SignUpViewModel

/**
 * 회원가입
 * @author jess
 */
class SignUpActivity : BaseActivity<SignupActivityBinding>() {

    private var vm = SignUpViewModel(ContextProvider(this))

    override fun getLayoutId() = R.layout.signup_activity

    override fun initDataBinding() {
        super.initDataBinding()
        dataBinding.vm = vm
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
