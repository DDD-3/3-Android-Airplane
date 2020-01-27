package com.ddd.airplane.presenter.chat.list.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.data.response.chat.ChatMessageData
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.databinding.ChatListFragmentBinding
import com.ddd.airplane.databinding.ChatListItemBinding
import com.ddd.airplane.databinding.ChatMsgItemBinding
import com.ddd.airplane.presenter.chat.list.viewmodel.ChatListViewModel
import com.ddd.airplane.presenter.chat.room.view.ChatRoomActivity
import com.ddd.airplane.presenter.chat.room.viewmodel.ChatMsgViewModel
import kotlinx.android.synthetic.main.chat_list_fragment.*
import kotlinx.android.synthetic.main.chat_list_fragment.rv_chat
import kotlinx.android.synthetic.main.chat_room_activity.*

/**
 * 채팅 리스트
 * @author jess
 */
class ChatListFragment : BaseFragment<ChatListFragmentBinding, ChatListViewModel>(),
    View.OnClickListener {

    override val layoutRes = R.layout.chat_list_fragment

    override val viewModelClass = ChatListViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {
        btn_test.setOnClickListener(this)

        rv_chat.apply {
            setHasFixedSize(true)
            adapter = object :
                BaseRecyclerViewAdapter<ChatRoomData, ChatListItemBinding>(R.layout.chat_list_item) {
            }.apply {
                setOnItemClickListener { view, data ->
                    context.showToast(data?.roomId.toString())
                }
            }
        }

    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.getChatList()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_test -> {
                startActivity(Intent(context, ChatRoomActivity::class.java))
            }
        }
    }
}
