package com.ddd.airplane.presenter.chat.room.view

import android.os.Bundle
import android.util.Log
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ChatRoomActivityBinding
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.presenter.chat.list.viewmodel.ChatRoomViewModel
import kotlinx.android.synthetic.main.chat_room_activity.*
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader

/**
 * 채팅
 * @author jess
 */
class ChatRoomActivity : BaseActivity<ChatRoomActivityBinding, ChatRoomViewModel>(),
    View.OnClickListener {

    override val layoutRes = R.layout.chat_room_activity
    override val viewModelClass = ChatRoomViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {
        tv_chat_msg.setOnClickListener(this)
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        val headerList: List<StompHeader> = listOf(StompHeader("access-token", "32c0ec88-3069-475e-9eaa-60e207c10b31"))

        val client = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://13.209.77.249/ws/websocket")
        client.connect(headerList)

        client.topic("/topic/room/1").subscribe { topicMessage ->
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_chat_msg -> {
                viewModel.sendChatMessage(et_chat_msg.text.toString())
            }
        }
    }
}
