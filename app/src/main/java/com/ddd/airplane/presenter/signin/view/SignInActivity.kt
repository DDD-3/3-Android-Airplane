package com.ddd.airplane.presenter.signin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import kotlinx.android.synthetic.main.signin_activity.*

/**
 * 로그인
 * @author jess
 */
class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)
        btn_login.setOnClickListener(View.OnClickListener {
            btn_kakao_login.performClick()
        })

    }
}
