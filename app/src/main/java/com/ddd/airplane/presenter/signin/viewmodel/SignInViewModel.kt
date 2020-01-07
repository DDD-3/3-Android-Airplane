package com.ddd.airplane.presenter.signin.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.common.manager.TokenManager

class SignInViewModel(application: Application) : BaseViewModel(application) {

    // 로그인 성공여부
    private val _isSucceed = MutableLiveData<Boolean>()
    val isSucceed: LiveData<Boolean> = _isSucceed

    /**
     * 로그인
     *
     * @param email
     * @param password
     */
    fun doSignIn(email: String, password: String) {
        // 토큰 발급
        TokenManager.getAccessToken(this, email, password) { isToken ->
            if (isToken) {
                MemberManager.setAccount(this, email) { isSignIn ->
                    isSignIn.let {
                        // listener 있으면 리턴 없으면 postValue
                        MemberManager.sigInInListener?.invoke(it) ?: _isSucceed.postValue(it)
                    }
                }
            }
        }
    }
}
