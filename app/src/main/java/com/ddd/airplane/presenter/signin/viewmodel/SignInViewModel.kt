package com.ddd.airplane.presenter.signin.viewmodel

import android.app.Application
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.extension.request
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.data.request.SignInTokenRequest
import com.ddd.airplane.data.response.TokenResponse
import com.ddd.airplane.data.response.ErrorResponse

class SignInViewModel(application: Application) : BaseViewModel(application) {

    /**
     * 로그인
     *
     * @param email
     * @param password
     */
    fun reqSignIn(email: String, password: String) {

        // 토큰 발급
        RetrofitManager
            .user
            .postSignInToken(SignInTokenRequest(email, password))
            .request(this, object : OnResponseListener<TokenResponse> {

                override fun onSuccess(response: TokenResponse) {

                }

                override fun onError(error: ErrorResponse) {

                }

            })
    }

}
