package com.ddd.airplane.presenter.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.manager.ChatRoomManager
import com.ddd.airplane.repository.database.RecentRepository
import com.ddd.airplane.repository.database.recent.RecentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : BaseViewModel(application) {

    // 닉네임
    private val _recent = MutableLiveData<RecentEntity>()
    val recent: LiveData<RecentEntity> = _recent

    // 최근 본 방송 roomId
    private var recentRoomId: Long = 0

    fun onResume() {
        getRecent()
    }

    fun onPause() {

    }

    /**
     * 최근 방송 검색
     */
    private fun getRecent() {

        viewModelScope.launch {

            RecentRepository().selectTopLimit()?.let { recent ->

                // 닫았는지 여부 판단
                if (recent.isFloatingClose) {
                    return@launch
                }

                recentRoomId = recent.roomId ?: 0
                withContext(Dispatchers.Main) {
                    _recent.value = recent
                }
            }
        }
    }

    /**
     * 최근 본 방송 닫기
     */
    fun setFloatingClose() {

    }

    /**
     * 최근 본 방송 접속
     */
    fun joinChatRoom() {
        ChatRoomManager.joinChatRoom(context, recentRoomId)
    }

}
