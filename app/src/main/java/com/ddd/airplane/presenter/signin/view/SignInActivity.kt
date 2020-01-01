package com.ddd.airplane.presenter.signin.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.databinding.SigninActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity

/**
 * 로그인
 * @author jess
 */
class SignInActivity : BaseActivity<SigninActivityBinding>(), View.OnClickListener {

    override fun setLayoutId(): Int {
        return R.layout.signin_activity
    }

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@SignInActivity
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_login -> {
//                val mainIntent = Intent(this, MainActivity::class.java)
//                mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(mainIntent)
            }
        }
    }
}
