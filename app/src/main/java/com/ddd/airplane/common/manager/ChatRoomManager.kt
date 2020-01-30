package com.ddd.airplane.common.manager

import android.content.Context
import android.content.Intent
import androidx.room.RoomDatabase
import com.ddd.airplane.R
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.data.response.chat.ProgramData
import com.ddd.airplane.presenter.chat.room.view.ChatRoomActivity
import com.ddd.airplane.repository.database.RoomManager
import com.ddd.airplane.repository.database.member.MemberEntity
import com.ddd.airplane.repository.database.recent.RecentEntity
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * 채팅방 매니저
 */
object ChatRoomManager {

    /**
     * 채팅방 이동
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
            RoomManager.instance.recentDao()
                .insert(
                    RecentEntity(
                        data.roomId,
                        data.subjectName,
                        data.subjectDescription,
                        data.subjectThumbnailUrl
                    )
                )
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d(">> insert recent succeed")
                }, {
                    Timber.d(">> insert recent failed")
                })
        }
    }
}