package com.ddd.airplane.data.response.chat

/**
 * 채팅방 메세지 목록
 */
data class ChatMessageData(
    val messages: List<MessageData>?
) {
    /**
     * 메세지
     */
    data class MessageData(
        val messageId: Long?,
        val roomId: Long?,
        val senderId: String?,
        val content: String?,
        val createAt: String?
    )
}

