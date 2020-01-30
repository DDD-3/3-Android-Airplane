package com.ddd.airplane.common.manager

import android.content.Context
import android.content.Intent
import com.ddd.airplane.presenter.chat.room.view.ChatRoomActivity

/**
 * 채팅방 매니저
 */
object ChatRoomManager {

    /**
     * 채팅방 이동
     *
     * @param context
     * @param roomId
     */
    fun joinChatRoom(context: Context?, roomId: Long?) {
        Intent(context, ChatRoomActivity::class.java).let {
            it.putExtra("roomId", roomId)
            context?.startActivity(it)
        }
    }
}