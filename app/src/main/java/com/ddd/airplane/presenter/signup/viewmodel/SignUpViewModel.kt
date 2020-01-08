package com.ddd.airplane.presenter.signup.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import com.ddd.airplane.data.request.SignUpRequest
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.data.response.user.SignUpData

/**
 * 회원가입 ViewModel
 */
class SignUpViewModel(application: Application) : BaseViewModel(application) {

    // 회원가입 성공여부
    private val _isSucceed = MutableLiveData<Boolean>()
    val isSucceed: LiveData<Boolean> = _isSucceed

    // 회원가입 성공여부
    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> = _nickName

    /**
     * 닉네임
     */
    fun getNickName() {

    }

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
            .request(this, object : OnResponseListener<SignUpData> {

                override fun onSuccess(response: SignUpData) {
                    _isSucceed.postValue(true)
                }

                override fun onError(error: ErrorData) {
                    _isSucceed.postValue(false)
                }

            })
    }
}
