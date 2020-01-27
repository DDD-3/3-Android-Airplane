package com.ddd.airplane.presenter.chat.room.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.manager.TokenManager
import com.ddd.airplane.common.utils.Utils
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.data.response.chat.ChatMessageData
import com.ddd.airplane.data.response.chat.ChatPayloadData
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.data.response.chat.ScheduleData
import com.ddd.airplane.repository.network.config.ServerInfo
import com.ddd.airplane.repository.network.config.ServerUrl
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader
import kotlin.collections.ArrayList

class ChatRoomViewModel(application: Application) : BaseViewModel(application) {
    private val client = Stomp.over(
        Stomp.ConnectionProvider.OKHTTP,
        ServerInfo.DOMAIN.REAL.domain + ServerUrl.WEB_SOCKET
    )

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

    private val _msgList = MutableLiveData<ArrayList<ChatMessageData.MessageData>>()
    val msgList: LiveData<ArrayList<ChatMessageData.MessageData>> = _msgList

    private val _roomId = MutableLiveData<Long>()
    private val _subjectId = MutableLiveData<Long>()

    //TODO chat api const 분리
    @SuppressLint("CheckResult")
    fun connectChatClient() {
        val headerList: List<StompHeader> =
            listOf(StompHeader("access-token", TokenManager.accessToken))

        client.apply {
            connect(headerList)
        }

        client.topic(ServerUrl.SUBSCRIBE_ROOM + _roomId.value)
            .doOnError {
                Timber.e(it.message)
            }
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d(it.payload)
                val res: ChatPayloadData = Gson().fromJson(it.payload, ChatPayloadData::class.java)
                handlePayLoadData(res)
            }, {
                Timber.e(it.message)
            })

        client
            .lifecycle()
            .subscribeOn(Schedulers.io())
            .doOnError {
                Timber.e(it.message)
            }
            .subscribe({
                when (it.type) {
                    LifecycleEvent.Type.OPENED -> Timber.e("Stomp connection opened")
                    LifecycleEvent.Type.CLOSED -> Timber.e("Stomp connection closed")
                }
            }, {
                Timber.e(it.message)
            })
    }

    private fun handlePayLoadData(res: ChatPayloadData) {
        when (res.type) {
            "JOIN" -> {
                //TODO 입장 메시지 넣을까?
            }
            "CHAT" -> {
                val data: ArrayList<ChatMessageData.MessageData>? = ArrayList()
                data?.add(
                    ChatMessageData.MessageData(
                        res.messageId,
                        res.roomId,
                        res.senderId,
                        res.content,
                        ""
                    )
                )
                _msgList.postValue(data)
            }
        }
    }

    fun disconnectChatClient() {
        client.disconnect()
    }

    fun sendChatMessage(msg: String) {
        val chatContent = "{\"type\": \"CHAT\", \"content\": \"$msg\"}"
        client.send(
            ServerUrl.SEND_MSG + _roomId.value + "/chat",
            chatContent
        ).subscribe()
    }

    fun getChatRoomInfo(roomId: Long) {
        RetrofitManager
            .chat
            .getRoom(roomId)
            .request(this, object : OnResponseListener<ChatRoomData> {
                override fun onSuccess(response: ChatRoomData) {
                    response.let { data ->
                        _subjectId.value = data.subjectId
                        _roomName.value = data.subjectName
                        _roomDesc.value = data.subjectDescription
//                        _roomSchedule.value = parseRoomSchedule(data.scheduleList)
                        _subscribeCount.value = data.subjectSubscribeCount.toString()
//                        _subscribed.value = data.subscribed
                    }
                    _roomId.value = response.roomId
                    _userCount.value = response.roomUserCount.toString()
                    _liked.value = response.liked
                    _msgList.value = response.messages
                    connectChatClient()
                }

                override fun onError(error: ErrorData) {
                    showToast("Error")
                }

            })
    }

    private fun parseRoomSchedule(scheduleList: List<ScheduleData>?): String? {
        //TODO start ~ end time 디자인 확인해서 넣기
        val startAt = scheduleList?.get(0)?.startAt
        return if (startAt != null) Utils.convertLongToTime(startAt) else R.string.error_info.toString()
    }

    fun postSubscribe() {
        val subId = _subjectId.value
        if (subId != null) {
            RetrofitManager
                .subscribe
                .postSubscribe(subId, subId)
                .request(this, object : OnResponseListener<Any> {
                    override fun onSuccess(response: Any) {
                        getChatRoomInfo(_roomId.value!!)
                    }

                    override fun onError(error: ErrorData) {
                    }

                })
        }
    }

    fun deleteSubscribe() {
        val subId = _subjectId.value
        if (subId != null) {
            RetrofitManager
                .subscribe
                .deleteSubscribe(subId)
                .request(this, object : OnResponseListener<Any> {
                    override fun onSuccess(response: Any) {
                        getChatRoomInfo(_roomId.value!!)
                    }

                    override fun onError(error: ErrorData) {
                    }

                })
        }
    }


    fun getChatMessages() {

        //TODO baseMsg, size 정의
        RetrofitManager
            .chat
            .getRoomMessages(_roomId.value!!, 0, 20, "BACKWARD")
            .request(this, object : OnResponseListener<ChatMessageData> {
                override fun onSuccess(response: ChatMessageData) {
                    _msgList.value = response.messages
                }

                override fun onError(error: ErrorData) {
                    showToast("Error")
                }

            })
    }

}
