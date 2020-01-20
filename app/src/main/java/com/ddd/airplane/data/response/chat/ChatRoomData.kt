package com.ddd.airplane.data.response.chat

/**
 * 채팅방 정보
 *
 * @property roomId
 * @property userCount
 * @property subject
 */
data class ChatRoomData(
    val roomId: Long?,
    val userCount: Int?,
    val subject: SubjectData?,
    val liked: Boolean?
)

