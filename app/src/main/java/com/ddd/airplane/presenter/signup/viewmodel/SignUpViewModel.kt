package com.ddd.airplane.presenter.signup.viewmodel

import android.app.Application
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.common.repository.network.retrofit.request
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.data.response.ErrorResponse
import com.ddd.airplane.common.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.data.request.SignUpRequest
import com.ddd.airplane.data.response.SignUpResponse

/**
 * 회원가입 ViewModel
 */
class SignUpViewModel(application: Application) : BaseViewModel(application) {

    /**
     *  회원가입
     *
     * @param email
     * @param password
     * @param nickName
     */
    fun doSignUp(email: String, password: String, nickName: String) {
        RetrofitManager
            .user
            .postAccounts(SignUpRequest(email, password, nickName))
            .request(this, object : OnResponseListener<SignUpResponse> {

                override fun onSuccess(response: SignUpResponse) {
                    // 회원정보
                    MemberManager.getAccount(this@SignUpViewModel, email) {
                        context.showToast(context.getString(R.string.sign_up_succeed))
                    }
                }

                override fun onError(error: ErrorResponse) {

                }

            })
    }
}
