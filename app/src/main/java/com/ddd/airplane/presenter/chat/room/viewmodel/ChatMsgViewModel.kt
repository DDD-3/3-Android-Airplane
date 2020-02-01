package com.ddd.airplane.presenter.chat.room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.R
import com.ddd.airplane.data.response.chat.ChatMessageData
import kotlin.properties.Delegates

class ChatMsgViewModel {
    var messageId: Long? = 0
    var senderId: String = ""
    var content: String = ""
    var textColor: Int? = 0

    fun setChatMsg(model: ChatMessageData.MessageData?) {
        model?.let { msg ->
            messageId = msg.messageId
            senderId = msg.senderId.toString()
            content = msg.content.toString()

            when (model?.messageId?.rem(11)?.toInt()) {
                0 -> { textColor = R.color.text_grey }
                1 -> { textColor = R.color.text_baby }
                2 -> { textColor = R.color.text_sky }
                3 -> { textColor = R.color.text_yellow }
                4 -> { textColor = R.color.text_pink }
                5 -> { textColor = R.color.text_blue }
                6 -> { textColor = R.color.text_navy }
                7 -> { textColor = R.color.text_orange }
                8 -> { textColor = R.color.text_green }
                9 -> { textColor = R.color.text_purple }
                10 -> { textColor = R.color.text_deep }
            }
        }
    }
}