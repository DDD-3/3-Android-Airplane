package com.ddd.airplane.presenter.signup.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import com.ddd.airplane.data.request.SignUpRequest
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.data.response.user.SignUpData
import com.ddd.airplane.repository.network.UserRepository
import kotlinx.coroutines.launch

/**
 * 회원가입 ViewModel
 */
class SignUpViewModel(application: Application) : BaseViewModel(application) {

    // 회원가입 성공여부
    private val _isSucceed = MutableLiveData<Boolean>()
    val isSucceed: LiveData<Boolean> = _isSucceed

    /**
     *  회원가입
     *
     * @param email
     * @param password
     * @param nickName
     */
    fun doSignUp(email: String, password: String, nickName: String) {
        viewModelScope.launch {
            UserRepository
                .setOnNetworkStatusListener(
                    this@SignUpViewModel.showProgress(true)
                )
                .setOnErrorListener {
                    _isSucceed.postValue(false)
                }
                .postAccounts(
                    SignUpRequest(
                        email,
                        password,
                        nickName
                    )
                )?.let { response ->
                    _isSucceed.postValue(true)
                }
        }
    }
}
