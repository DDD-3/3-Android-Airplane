package com.ddd.airplane.presenter.signin.viewmodel

import android.app.Application
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.common.manager.TokenManager

class SignInViewModel(application: Application) : BaseViewModel(application) {

    /**
     * 로그인
     *
     * @param email
     * @param password
     */
    fun doSignIn(email: String, password: String) {
        // 토큰 발급
        TokenManager.getAccessToken(this, email, password) {
            if (it) {
                getProfile(email)
            }
        }
    }

    /**
     * 계정 조회
     *
     * @param email
     */
    private fun getProfile(email: String) {
        MemberManager.getAccount(this, email) { isSignIn ->
            MemberManager.sigInInListener?.invoke(isSignIn)
            MemberManager.sigInInListener = null
        }
    }
}
