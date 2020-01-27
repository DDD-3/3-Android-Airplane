package com.ddd.airplane.common.manager

import android.content.Context
import android.content.Intent
import com.ddd.airplane.presenter.chat.room.view.ChatRoomActivity

/**
 * 채팅방으로 이동
 */
object ChatRoomManager {
    fun joinChatRoom(context: Context?, roomId: Long) {
        val intent = Intent(context, ChatRoomActivity::class.java)
        intent.putExtra("roomId", roomId)
        context?.startActivity(intent)
    }
}