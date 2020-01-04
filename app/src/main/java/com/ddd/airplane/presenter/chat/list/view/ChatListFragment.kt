package com.ddd.airplane.presenter.chat.list.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ChatListFragmentBinding
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.presenter.chat.list.viewmodel.ChatListViewModel

/**
 * 채팅 리스트
 * @author jess
 */
class ChatListFragment : BaseFragment<ChatListFragmentBinding, ChatListViewModel>() {

    override val layoutRes = R.layout.chat_list_fragment

    override val viewModelClass = ChatListViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
