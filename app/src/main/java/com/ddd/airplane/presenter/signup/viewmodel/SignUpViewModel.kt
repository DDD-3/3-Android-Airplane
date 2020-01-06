package com.ddd.airplane.presenter.signup.viewmodel

import android.app.Application
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.extension.request
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.data.response.ErrorResponse
import com.ddd.airplane.common.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.data.request.SignUpRequest
import com.ddd.airplane.data.response.SignUpResponse

class SignUpViewModel(application: Application) : BaseViewModel(application) {

    fun reqSignUp(email: String, password: String, nickName: String) {
        RetrofitManager
            .user
            .postAccounts(SignUpRequest("jess@test.com", password, nickName))
            .request(this, object : OnResponseListener<SignUpResponse> {

                override fun onSuccess(response: SignUpResponse) {

                }

                override fun onError(error: ErrorResponse) {

                }

            })
    }
}
