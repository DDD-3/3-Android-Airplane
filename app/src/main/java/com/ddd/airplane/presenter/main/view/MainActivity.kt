package com.ddd.airplane.presenter.main.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.common.provider.ContextProvider
import com.ddd.airplane.databinding.MainActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity
import com.ddd.airplane.presenter.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*

/**
 * 메인
 * @author jess
 */
class MainActivity : BaseActivity<MainActivityBinding>(), View.OnClickListener {

    private var vm = MainViewModel(ContextProvider(this))

    override fun getLayoutId() = R.layout.main_activity

    override fun initDataBinding() {
        super.initDataBinding()
        dataBinding.vm = vm
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
