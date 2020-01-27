package com.ddd.airplane.data.response.chat

/**
 * 채팅방 정보
 */
data class ChatRoomData(
    val roomId: Long? = 0,
    val roomUserCount: Int? = 0,

    val liked: Boolean? = false,

    val subjectId: Long? = 0,
    val subjectName: String? = null,
    val subjectDescription: String? = null,
    val subjectSubscribeCount: Int? = 0,
    val subjectThumbnailUrl: String? = null,

    val subjectSubscribed: Boolean? = false,
    val upcomingSubjectSchedule: ScheduleData? = null,

    val recentMessages: ArrayList<ChatMessageData.MessageData>? = null
)