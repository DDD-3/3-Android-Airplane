package com.ddd.airplane.presenter.chat.list.view

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ChatListFragmentBinding
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.presenter.chat.list.viewmodel.ChatListViewModel
import com.ddd.airplane.presenter.chat.room.view.ChatRoomActivity
import kotlinx.android.synthetic.main.chat_list_fragment.*
import kotlinx.android.synthetic.main.chat_room_activity.*

/**
 * 채팅 리스트
 * @author jess
 */
class ChatListFragment : BaseFragment<ChatListFragmentBinding, ChatListViewModel>() ,
    View.OnClickListener {

    override val layoutRes = R.layout.chat_list_fragment

    override val viewModelClass = ChatListViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {
        btn_test.setOnClickListener(this)
    }

    override fun onCreated(savedInstanceState: Bundle?) {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_test -> {
                startActivity(Intent(context, ChatRoomActivity::class.java))
            }
        }
    }
}
