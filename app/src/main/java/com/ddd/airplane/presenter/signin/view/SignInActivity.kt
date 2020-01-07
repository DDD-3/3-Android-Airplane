package com.ddd.airplane.presenter.signin.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.databinding.SigninActivityBinding
import com.ddd.airplane.presenter.signin.viewmodel.SignInViewModel
import com.ddd.airplane.presenter.signup.view.SignUpActivity
import kotlinx.android.synthetic.main.signin_activity.*

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
        viewModel.run {
            isSucceed.observe(this@SignInActivity, Observer {

            })
        }
    }

    override fun initLayout() {
        val views = arrayOf(bt_sign_in, cl_sign_up_info)
        views.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_sign_in -> {
                viewModel.doSignIn("jess@test.com", "aaaaaa")
//                viewModel.doSignIn(et_email.text, et_password.text)
            }

            R.id.cl_sign_up_info -> {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }
    }
}
