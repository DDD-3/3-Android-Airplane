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
    val subject: SubjectData?
)

/**
 * 프그램의 주제
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
    val scheduleList: ArrayList<Any>?, // TODO Any 타입 지정해야함 (API 에서 확인 불가능했음)
    val subscribeCount: Int?
)

