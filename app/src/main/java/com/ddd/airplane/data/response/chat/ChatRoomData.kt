package com.ddd.airplane.data.response.chat


/**
 * 채팅방 정보
 *
 * @property roomId
 * @property userCount
 * @property subject
 */
data class ChatRoomData(
    val roomId: Int?,
    val userCount: Int?,
    val subject: SubjectData?,
    val liked: Boolean?
)

/**
 * 프로그램의 주제
 *
 * @property subjectId
 * @property name
 * @property description
 * @property scheduleList
 * @property subscribeCount
 */
data class SubjectData(
    val subjectId: Int,
    val name: String?,
    val description: String?,
    val scheduleList: ArrayList<Schedule>?,
    val subscribeCount: Int?
)

/**
 * 프로그램 스케줄
 *
 * @property startAt
 * @property endAt
 */
data class Schedule(
    val startAt: Long,
    val endAt: Long
)

