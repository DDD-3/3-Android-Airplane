package com.ddd.airplane.presenter.mypage.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.data.response.SubscribeData
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request

class MyPageViewModel(application: Application) : BaseViewModel(application) {

    // 닉네임
    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> = _nickName

    // 구독 리스트
    private val _subscribeList = MutableLiveData<ArrayList<ChatRoomData>>()
    val subscribeList: LiveData<ArrayList<ChatRoomData>> = _subscribeList

    // 페이지 넘버
    private var pageNum = 1

    init {
        setProfile()
        getSubscribe()
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

    /**
     * 구독방송 리스트 조회
     */
    private fun getSubscribe() {
        RetrofitManager
            .subscribe
            .getSubscribe(pageNum)
            .request(this, object : OnResponseListener<SubscribeData> {

                override fun onSuccess(response: SubscribeData) {
                    _subscribeList.postValue(response.items)
                }

                override fun onError(error: ErrorData) {

                }
            })
    }
}
