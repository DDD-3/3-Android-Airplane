package com.ddd.airplane.presenter.chat.room.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
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
    View.OnClickListener, View.OnFocusChangeListener {

    override val layoutRes = R.layout.chat_room_activity
    override val viewModelClass = ChatRoomViewModel::class.java
    //TODO 리스트에서 id 받아서 set
    private var roomId = 2

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initLayout() {
        tv_send_msg.setOnClickListener(this)
        ib_hold_info.setOnClickListener(this)
        tv_subscribe_room.setOnClickListener(this)
        tv_subscribe_cancel_room.setOnClickListener(this)
        et_chat_msg.onFocusChangeListener = this
        val llm = LinearLayoutManager(this)
        llm.stackFromEnd = true
        llm.reverseLayout = false
        rv_chat.layoutManager = llm
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.getChatRoomInfo(roomId)
        viewModel.connectChatClient()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getChatMessages()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_send_msg -> {
                viewModel.sendChatMessage(et_chat_msg.text.toString())
            }
            R.id.ib_hold_info -> {
                cl_info_second.visibility = if(cl_info_second.visibility == View.GONE) View.VISIBLE else View.GONE
                cl_info_third.visibility = if(cl_info_third.visibility == View.GONE) View.VISIBLE else View.GONE
            }
            R.id.tv_subscribe_room -> {
                viewModel.postSubscribe()
            }
            R.id.tv_subscribe_cancel_room -> {
                viewModel.deleteSubscribe()
            }
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if(hasFocus) {
            tv_send_msg.visibility = View.VISIBLE
            btn_room_like.visibility = View.GONE
        } else {
            tv_send_msg.visibility = View.GONE
            btn_room_like.visibility = View.VISIBLE
        }

    }
}
