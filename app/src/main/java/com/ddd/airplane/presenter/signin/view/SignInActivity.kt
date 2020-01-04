package com.ddd.airplane.presenter.signin.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.common.provider.ContextProvider
import com.ddd.airplane.databinding.SigninActivityBinding
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.presenter.signin.viewmodel.SignInViewModel

/**
 * 로그인
 * @author jess
 */
class SignInActivity : BaseActivity<SigninActivityBinding, SignInViewModel>(),
    View.OnClickListener {

    override val layoutRes = R.layout.signin_activity
    override val viewModelClass = SignInViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

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
