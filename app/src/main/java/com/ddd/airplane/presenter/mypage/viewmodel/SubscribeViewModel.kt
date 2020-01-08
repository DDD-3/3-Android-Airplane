package com.ddd.airplane.presenter.mypage.viewmodel

import androidx.databinding.ObservableField
import com.ddd.airplane.data.response.chat.ChatRoomData

class SubscribeViewModel {

    var roomId: Int? = 0
    val userCount = ObservableField<Int>()
    val name = ObservableField<String>()
    val description = ObservableField<String>()
    val subscribeCount = ObservableField<Int>()

    fun setData(model: ChatRoomData) {
        model.let { room ->
            roomId = room.roomId
            userCount.set(room.userCount)
            room.subject?.let { subject ->
                name.set(subject.name)
                description.set(subject.description)
                subscribeCount.set(subject.subscribeCount)
            }
        }
    }


}
