package com.ddd.airplane.presenter.signin.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.common.provider.ContextProvider
import com.ddd.airplane.databinding.SigninActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity
import com.ddd.airplane.presenter.signin.viewmodel.SignInViewModel

/**
 * 로그인
 * @author jess
 */
class SignInActivity : BaseActivity<SigninActivityBinding>(), View.OnClickListener {

    private var vm = SignInViewModel(ContextProvider(this))

    override fun getLayoutId() = R.layout.signin_activity

    override fun initDataBinding() {
        super.initDataBinding()
        dataBinding.vm = vm
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_login -> {

            }
        }
    }
}
