package com.ddd.airplane.common.manager

import android.content.Context
import android.content.Intent
import com.ddd.airplane.R
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.data.response.chat.ProgramData
import com.ddd.airplane.presenter.chat.room.view.ChatRoomActivity
import com.ddd.airplane.repository.database.RecentRepository
import com.ddd.airplane.repository.database.room.RoomManager
import com.ddd.airplane.repository.database.recent.RecentEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * 채팅방 매니저
 */
object ChatRoomManager {

    /**
     * 채팅방 이동
     */
    fun joinChatRoom(context: Context?, roomId: Long) {
        // 채팅방 이동
        Intent(context, ChatRoomActivity::class.java).let {
            it.putExtra("roomId", roomId)
            context?.startActivity(it)
        }
    }

    /**
     * 채팅방 이동, DB 저장
     */
    fun joinChatRoom(context: Context?, program: ProgramData?) {
        program?.let { data ->
            // 채팅방 이동
            Intent(context, ChatRoomActivity::class.java).let {
                it.putExtra("roomId", data.roomId)
                context?.startActivity(it)
            }

            // 최근 들어간 채팅
            setRecentChatRoom(program)

        } ?: context?.showToast(R.string.error_chat_data)
    }

    /**
     * Room 에 데이터 저장
     */
    private fun setRecentChatRoom(program: ProgramData?) {
        program?.let { data ->
            RecentRepository().insertRecent(
                RecentEntity(
                    data.roomId,
                    data.subjectName,
                    data.subjectDescription,
                    data.subjectThumbnailUrl
                )
            )
        }
    }
}