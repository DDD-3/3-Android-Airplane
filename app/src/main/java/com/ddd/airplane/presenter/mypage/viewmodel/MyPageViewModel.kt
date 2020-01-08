package com.ddd.airplane.presenter.mypage.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.manager.MemberManager

class MyPageViewModel(application: Application) : BaseViewModel(application) {

    // 닉네임
    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> = _nickName

    init {
        setProfile()
    }

    /**
     * 프로필 정보 세팅
     */
    private fun setProfile() {
        MemberManager.getAccount {
            it?.run {
                _nickName.postValue(nickName)
            }
        }
    }
}
