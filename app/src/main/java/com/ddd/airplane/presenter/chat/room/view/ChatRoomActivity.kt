package com.ddd.airplane.presenter.chat.room.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ChatRoomActivityBinding
import com.ddd.airplane.common.base.BaseActivity
import com.ddd.airplane.presenter.chat.list.viewmodel.ChatRoomViewModel

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

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {

    }
}
