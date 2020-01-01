package com.ddd.airplane.presenter.main.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.databinding.MainActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity
import kotlinx.android.synthetic.main.main_activity.*

/**
 * 메인
 * @author jess
 */
class MainActivity : BaseActivity<MainActivityBinding>(), View.OnClickListener {

    override fun setLayoutId() = R.layout.main_activity

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@MainActivity
        }
    }

    override fun initLayout() {
        bnv_main.run {
            fragmentManager = supportFragmentManager
            init()
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }
}
