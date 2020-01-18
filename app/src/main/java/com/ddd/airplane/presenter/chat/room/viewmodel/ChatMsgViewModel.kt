package com.ddd.airplane.presenter.chat.room.viewmodel

import androidx.databinding.ObservableField
import com.ddd.airplane.data.response.chat.MessageData

class ChatMsgViewModel {
    var messageId: Int? = 0
    val roomId = ObservableField<Int>()
    val senderId = ObservableField<String>()
    val content = ObservableField<String>()
    val createAt = ObservableField<String>()

    fun setChatMsg(model: MessageData) {
        model.let { msg ->
            messageId = msg.messageId
            roomId.set(msg.roomId)
            senderId.set(msg.senderId)
            content.set(msg.content)
            createAt.set(msg.createAt)
        }
    }
}