package com.ddd.airplane.data.response.chat

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
    val subjectId: Long = 0,
    val name: String? = null,
    val description: String? = null,
    val scheduleList: List<ScheduleData>? = arrayListOf(),
    val subscribeCount: Int? = 0,
    val thumbnail: String? = null,
    val subscribed: Boolean = false
)