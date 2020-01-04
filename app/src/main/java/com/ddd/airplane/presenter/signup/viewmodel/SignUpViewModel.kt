package com.ddd.airplane.presenter.signup.viewmodel

import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.extension.request
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.model.ErrorResponse
import com.ddd.airplane.common.network.retrofit.RetrofitManager

class SignUpViewModel : BaseViewModel() {

    fun reqSignUp(email: String, password: String, nickName: String) {

        RetrofitManager
            .user.postAccounts(email, password, nickName)
            .request(object : OnResponseListener<Any> {

                override fun onSuccess(t: Any) {

                }

                override fun onError(error: ErrorResponse?) {

                }
                
            }, isProgress, isError)

    }
}
