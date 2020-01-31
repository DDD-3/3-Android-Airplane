package com.ddd.airplane.presenter.main.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.common.manager.TokenManager
import com.ddd.airplane.databinding.MainActivityBinding
import com.ddd.airplane.presenter.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*

/**
 * 메인
 * @author jess
 */
class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>(), View.OnClickListener {

    override val layoutRes = R.layout.main_activity
    override val viewModelClass = MainViewModel::class.java

    override fun initLayout() {
        bnv_main.run {
            fragmentManager = supportFragmentManager
            init()
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }
}
