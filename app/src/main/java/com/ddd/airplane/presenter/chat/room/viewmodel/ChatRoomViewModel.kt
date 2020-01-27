package com.ddd.airplane.presenter.chat.room.viewmodel

import android.annotation.SuppressLint
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
import com.ddd.airplane.data.response.chat.ScheduleData
import com.ddd.airplane.repository.network.config.ServerInfo
import com.ddd.airplane.repository.network.config.ServerUrl
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader
import java.util.*

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

    private val _msgList = MutableLiveData<ArrayList<ChatMessageData.MessageData>>()
    val msgList: LiveData<ArrayList<ChatMessageData.MessageData>> = _msgList

    private val _roomId = MutableLiveData<Long>()
    private val _subjectId = MutableLiveData<Long>()

    //TODO chat api const 분리
    @SuppressLint("CheckResult")
    fun connectChatClient() {
        val headerList: List<StompHeader> =
            listOf(StompHeader("access-token", TokenManager.accessToken))

        client = Stomp.over(
            Stomp.ConnectionProvider.OKHTTP,
            ServerInfo.DOMAIN.REAL.domain + ServerUrl.WEB_SOCKET
        ).apply {
            connect(headerList)
        }

        client.topic(ServerUrl.SUBSCRIBE_ROOM + _roomId.value)
            .doOnError {
                Timber.e(it.message)
            }
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d(it.payload)
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

    fun disconnectChatClient() {
        client.disconnect()
    }

    fun sendChatMessage(msg: String) {
        val chatContent = "{type: 'CHAT', content: $msg}"
        client.send(
            ServerInfo.DOMAIN.REAL.domain + ServerUrl.SEND_MSG + _roomId.value + "chat",
            chatContent
        )
    }

    fun getChatRoomInfo(roomId: Long) {
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

    private fun parseRoomSchedule(scheduleList: List<ScheduleData>?): String? {
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


    fun getChatMessages() {
        //testData
        val testMsgList = ArrayList<ChatMessageData.MessageData>()
        testMsgList.add(ChatMessageData.MessageData(0, 2, "id1", "content1", "createAt"))
        testMsgList.add(ChatMessageData.MessageData(0, 2, "id2", "content2", "createAt"))
        _msgList.value = testMsgList

        //TODO baseMsg, size 정의
//        RetrofitManager
//            .chat
//            .getRoomMessages(_roomId.value!!, 0, 20, "BACKWARD")
//            .request(this, object : OnResponseListener<ChatMessageData> {
//                override fun onSuccess(response: ChatMessageData) {
//                    TODO("메시지 목록에 추가")
//                }
//
//                override fun onError(error: ErrorData) {
//                    TODO("오류처리")
//                }
//
//            })
    }

}
