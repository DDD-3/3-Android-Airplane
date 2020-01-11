package com.ddd.airplane.presenter.chat.list.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.manager.TokenManager
import com.ddd.airplane.common.utils.Utils
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.data.response.chat.ChatMessageData
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.data.response.chat.Schedule
import com.ddd.airplane.repository.network.config.ServerInfo
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader
import java.util.ArrayList

class ChatRoomViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var client: StompClient

    private val _roomName = MutableLiveData<String>()
    val roomName: LiveData<String> = _roomName

    private val _roomDesc = MutableLiveData<String>()
    val roomDesc: LiveData<String> = _roomDesc

    private val _roomSchedule = MutableLiveData<String>()
    val roomSchedule: LiveData<String> = _roomSchedule

    private val _subscribeCount = MutableLiveData<String>()
    val subscribeCount: LiveData<String> = _subscribeCount

    private val _userCount = MutableLiveData<String>()
    val userCount: LiveData<String> = _userCount

    private val _liked = MutableLiveData<Boolean>()
    val liked: LiveData<Boolean> = _liked

    private val _subscribed = MutableLiveData<Boolean>(false)
    val subscribed: LiveData<Boolean> = _subscribed

    private val _roomId = MutableLiveData<Int>()
    private val _subjectId = MutableLiveData<Int>()

    //TODO chat api const 분리
    fun connectChatClient() {
        val headerList: List<StompHeader> =
            listOf(StompHeader("access-token", getToken()))
        client = Stomp.over(Stomp.ConnectionProvider.OKHTTP, ServerInfo.DOMAIN.REAL.domain + "/ws/websocket")
        client.connect(headerList)

        client.topic("/topic/room/" + _roomId.value).subscribe { topicMessage ->
            Log.d("TEST", topicMessage.payload)
        }


        client.lifecycle().subscribe(fun(it: LifecycleEvent) {
            when (it.type) {
                LifecycleEvent.Type.OPENED -> Log.d("TEST", "Stomp connection opened")
                LifecycleEvent.Type.ERROR -> Log.d("TEST", "Error", it.exception)
                LifecycleEvent.Type.CLOSED -> Log.d("TEST", "Stomp connection closed")
            }
        })
    }

    fun disconnectChatClient() {
        client.disconnect()
    }

    fun sendChatMessage(msg: String) {
        val chatContent = "{type: 'CHAT', content: $msg}"
        client.send(ServerInfo.DOMAIN.REAL.domain + "/app/room/" + _roomId.value + "chat", chatContent)
    }

    fun getChatRoomInfo(roomId: Int) {
        RetrofitManager
            .chat
            .getRoom(roomId)
            .request(this, object : OnResponseListener<ChatRoomData> {
                override fun onSuccess(response: ChatRoomData) {
                    response.subject?.let { subject ->
                        _subjectId.postValue(subject.subjectId)
                        _roomName.postValue(subject.name)
                        _roomDesc.postValue(subject.description)
                        _roomSchedule.postValue(parseRoomSchedule(subject.scheduleList))
                        _subscribeCount.postValue(subject.subscribeCount.toString())
                    }
                    _roomId.postValue(response.roomId)
                    _userCount.postValue(response.userCount.toString())
                    _liked.postValue(response.liked)
                }

                override fun onError(error: ErrorData) {
                    showToast("Error")
                }

            })
    }

    private fun parseRoomSchedule(scheduleList: ArrayList<Schedule>?): String? {
        //TODO start ~ end time 디자인 확인해서 넣기
        val startAt = scheduleList?.get(0)?.startAt
        return if (startAt != null) Utils.convertLongToTime(startAt) else R.string.error_info.toString()
    }

    fun postSubscribe() {
        RetrofitManager
            .subscribe
            .postSubscribe(_subjectId.value!!)
            .request(this, object : OnResponseListener<Any> {
                override fun onSuccess(response: Any) {
                    getChatRoomInfo(_roomId.value!!)
                    _subscribed.postValue(!subscribed.value!!)
                }

                override fun onError(error: ErrorData) {
                }

            })
    }

    fun deleteSubscribe() {
        RetrofitManager
            .subscribe
            .deleteSubscribe(_subjectId.value!!)
            .request(this, object : OnResponseListener<Any> {
                override fun onSuccess(response: Any) {
                    getChatRoomInfo(_roomId.value!!)
                    _subscribed.postValue(!subscribed.value!!)
                }

                override fun onError(error: ErrorData) {
                }

            })
    }


    fun getChatMessages(baseMsgId: Int) {
        RetrofitManager
            .chat
            .getRoomMessages(_roomId.value!!, baseMsgId, 20, "BACKWARD")
            .request(this, object : OnResponseListener<ChatMessageData> {
                override fun onSuccess(response: ChatMessageData) {
                    TODO("메시지 목록에 추가")
                }

                override fun onError(error: ErrorData) {
                    TODO("오류처리")
                }

            })
    }

    private fun getToken(): String = if (TokenManager.isExist()) {
        "${TokenManager.tokenType} ${TokenManager.accessToken}"
    } else {
        "Basic Y2xpZW50SWQ6Y2xpZW50U2VjcmV0"
    }

}
