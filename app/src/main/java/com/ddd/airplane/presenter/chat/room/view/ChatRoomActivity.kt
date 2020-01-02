package com.ddd.airplane.presenter.chat.room.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ChatRoomActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity

/**
 * 채팅
 * @author jess
 */
class ChatRoomActivity : BaseActivity<ChatRoomActivityBinding>(), View.OnClickListener {

    override fun getLayoutId() = R.layout.chat_room_activity

    override fun initDataBinding() {
        super.initDataBinding()
        dataBinding.run {

        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {

    }
}
