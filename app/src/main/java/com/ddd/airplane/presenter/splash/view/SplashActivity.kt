package com.ddd.airplane.presenter.splash.view

import android.content.Intent
import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.SplashActivityBinding
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.presenter.main.view.MainActivity
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
            doInitFlow {
                this@SplashActivity.finish()
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
