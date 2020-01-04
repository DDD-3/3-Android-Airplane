package com.ddd.airplane.presenter.splash.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.SplashActivityBinding
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.presenter.splash.viewmodel.SplashViewModel

/**
 * 스플래시
 * @author jess
 */
class SplashActivity : BaseActivity<SplashActivityBinding, SplashViewModel>() {

    override val layoutRes = R.layout.splash_activity
    override val viewModelClass = SplashViewModel::class.java

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
