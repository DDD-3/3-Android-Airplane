package com.ddd.airplane.presenter.splash.view

import android.content.Intent
import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.SplashActivityBinding
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.presenter.main.view.MainActivity
import com.ddd.airplane.presenter.signin.view.SignInActivity
import com.ddd.airplane.presenter.splash.viewmodel.SplashViewModel

/**
 * 스플래시
 * @author jess
 */
class SplashActivity : BaseActivity<SplashActivityBinding, SplashViewModel>() {

    override val layoutRes = R.layout.splash_activity
    override val viewModelClass = SplashViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()
        viewModel.run {
            doInitFlow { isSignIn ->
                this@SplashActivity.finish()
                if (isSignIn) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } else {
                    MemberManager.signIn(this@SplashActivity) {
                        if (it) {
                            startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
                        }
                    }
                }
            }
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
