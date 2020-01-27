package com.ddd.airplane.data.response.chat

/**
 * 채팅방 정보
 *
 * @property roomId
 * @property userCount
 * @property subject
 */
data class ChatRoomData(
    val roomId: Long? = 0,
    val userCount: Int? = 0,
    val subject: SubjectData? = null,
    val liked: Boolean? = false,
    val messages: ArrayList<ChatMessageData.MessageData>? = null
)

