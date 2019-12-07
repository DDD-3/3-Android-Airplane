package com.ddd.airplane.presenter.chat.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ChatListFragmentBinding
import com.ddd.airplane.presenter.base.view.BaseFragment

/**
 * 채팅 리스트
 * @author jess
 */
class ChatListFragment : BaseFragment<ChatListFragmentBinding>() {

    override fun getLayoutId() = R.layout.chat_list_fragment

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@ChatListFragment
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
