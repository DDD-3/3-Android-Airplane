package com.ddd.airplane.presenter.chat.room.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.utils.DeviceUtils
import com.ddd.airplane.data.response.chat.ChatMessageData
import com.ddd.airplane.databinding.ChatMsgItemBinding
import com.ddd.airplane.databinding.ChatRoomActivityBinding
import com.ddd.airplane.presenter.chat.room.viewmodel.ChatMsgViewModel
import com.ddd.airplane.presenter.chat.room.viewmodel.ChatRoomViewModel
import kotlinx.android.synthetic.main.chat_room_activity.*

/**
 * 채팅
 * @author jess
 */
class ChatRoomActivity : BaseActivity<ChatRoomActivityBinding, ChatRoomViewModel>(),
    View.OnClickListener, View.OnFocusChangeListener {

    override val layoutRes = R.layout.chat_room_activity
    override val viewModelClass = ChatRoomViewModel::class.java
    //TODO 리스트에서 id 받아서 set
    private var roomId: Long = 0

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initLayout() {
        tv_send_msg.setOnClickListener(this)
        iv_hold_info.setOnClickListener(this)
        tv_subscribe_room.setOnClickListener(this)
        tv_subscribe_cancel_room.setOnClickListener(this)
        et_chat_msg.onFocusChangeListener = this

        //recycler view
        val llm = LinearLayoutManager(this)
        llm.stackFromEnd = true
        llm.reverseLayout = false
        rv_chat.layoutManager = llm

        rv_chat.apply {
            setHasFixedSize(true)
            adapter = object :
                BaseRecyclerViewAdapter<ChatMessageData.MessageData, ChatMsgItemBinding>(R.layout.chat_msg_item) {

                override fun onBindData(
                    position: Int,
                    data: ChatMessageData.MessageData?,
                    dataBinding: ChatMsgItemBinding
                ) {
                    dataBinding.viewModel = ChatMsgViewModel().apply {
                        setChatMsg(data)
                    }
                }

            }
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        if (intent.hasExtra("roomId")) {
            roomId = intent.getLongExtra("roomId", 0)
        }
        viewModel.getChatRoomInfo(roomId)
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.disconnectChatClient()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_send_msg -> {
                viewModel.sendChatMessage(et_chat_msg.text.toString())
                et_chat_msg.text.clear()
                DeviceUtils.hideKeyboard(v)
            }
            R.id.iv_hold_info -> {
                cl_info_second.visibility =
                    if (cl_info_second.visibility == View.GONE) View.VISIBLE else View.GONE
                cl_info_third.visibility =
                    if (cl_info_third.visibility == View.GONE) View.VISIBLE else View.GONE
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
        if (hasFocus) {
            tv_send_msg.visibility = View.VISIBLE
            btn_room_like.visibility = View.GONE
        } else {
            tv_send_msg.visibility = View.GONE
            btn_room_like.visibility = View.VISIBLE
        }

    }
}
