package com.ddd.airplane.presenter.chat.room.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
import com.ddd.airplane.repository.network.ChatRepository
import com.ddd.airplane.repository.network.SubscribeRepository
import com.ddd.airplane.repository.network.config.ServerInfo
import com.ddd.airplane.repository.network.config.ServerUrl
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
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

    private val _roomData = MutableLiveData<ChatRoomData>()
    val roomData: LiveData<ChatRoomData> = _roomData
    
    private val _roomSchedule = MutableLiveData<String>()
    val roomSchedule: LiveData<String> = _roomSchedule

    private val _liked = MutableLiveData<Boolean>()
    val liked: LiveData<Boolean> = _liked

    private val _msgList = MutableLiveData<ArrayList<ChatMessageData.MessageData>>()
    val msgList: LiveData<ArrayList<ChatMessageData.MessageData>> = _msgList

    var roomId: Long = 0
        private set
    private val _subjectId = MutableLiveData<Long>()

    //TODO chat api const 분리
    @SuppressLint("CheckResult")
    fun connectChatClient() {

        if (roomId < 0) {
            return
        }

        val headerList: List<StompHeader> =
            listOf(StompHeader("access-token", TokenManager.accessToken))

        client.run {

            // connect
            connect(headerList)

            // topic
            topic(ServerUrl.SUBSCRIBE_ROOM + roomId)
                .doOnError {
                    Timber.e(it.message)
                }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d(it.payload)
                    val res: ChatPayloadData =
                        Gson().fromJson(it.payload, ChatPayloadData::class.java)
                    handlePayLoadData(res)
                }, {
                    Timber.e(it.message)
                })

            // lifecycle
            lifecycle()
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
            ServerUrl.SEND_MSG + roomId + "/chat",
            chatContent
        ).subscribe()
    }

    /**
     * 채팅방 정보
     *
     * @param roomId
     */
    fun getChatRoomInfo(roomId: Long) {

        this.roomId = roomId

        viewModelScope.launch {
            ChatRepository
                .setOnNetworkStatusListener(
                    this@ChatRoomViewModel.showProgress(true)
                )
                .setOnErrorListener {
                    showToast(it?.message)
                }
                .getRoomInfo(roomId)
                ?.let { response ->

                    _roomData.value = response

                    _subjectId.value = response.subjectId
                    _roomSchedule.value = parseRoomSchedule(response.upcomingSubjectSchedule)
                    _liked.value = response.liked
                    _msgList.value = response.recentMessages

                    connectChatClient()
                }

        }
    }

    private fun parseRoomSchedule(schedule: ScheduleData?): String? {
        //TODO start ~ end time 디자인 확인해서 넣기
        val startAt = schedule?.startAt
        var res = R.string.error_info.toString()

        if (startAt != null) {
            res = Utils.convertLongToYearDayTime(startAt)
        }

        return res
    }

    /**
     * 구독하기
     */
    fun postSubscribe() {
        viewModelScope.launch {
            _subjectId.value?.let {

                var isSucceed = true
                SubscribeRepository
                    .setOnNetworkStatusListener(
                        this@ChatRoomViewModel.showProgress(true)
                    )
                    .setOnErrorListener {
                        isSucceed = false
                    }
                    .postSubscribe(it)

                if (isSucceed) {
                    getChatRoomInfo(roomId!!)
                }
            }
        }
    }

    /**
     * 구독 취소하기
     */
    fun deleteSubscribe() {
        viewModelScope.launch {
            _subjectId.value?.let {

                var isSucceed = true
                SubscribeRepository
                    .setOnNetworkStatusListener(
                        this@ChatRoomViewModel.showProgress(true)
                    )
                    .setOnErrorListener {
                        isSucceed = false
                    }
                    .deleteSubscribe(it)

                if (isSucceed) {
                    getChatRoomInfo(roomId)
                }
            }
        }
    }

    /**
     * 채팅방 메세지 조회
     *
     */
    fun getChatMessages() {
        //TODO baseMsg, size 정의
        viewModelScope.launch {
            ChatRepository
                .setOnNetworkStatusListener(
                    this@ChatRoomViewModel.showProgress(true)
                )
                .setOnErrorListener {
                    showToast(it?.message)
                }
                .getRoomMessages(roomId, 0, 20, "BACKWARD")
                ?.let { response ->
                    _msgList.value = response.messages
                }
        }
    }
}
