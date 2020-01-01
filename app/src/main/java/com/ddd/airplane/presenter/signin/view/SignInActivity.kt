package com.ddd.airplane.presenter.signin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.presenter.main.view.MainActivity
import kotlinx.android.synthetic.main.signin_activity.*

/**
 * 로그인
 * @author jess
 */
class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)
        btn_login.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mainIntent)
        }
    }
}
