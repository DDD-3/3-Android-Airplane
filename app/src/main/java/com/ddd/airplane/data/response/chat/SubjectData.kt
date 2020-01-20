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
    val subjectId: Long,
    val name: String?,
    val description: String?,
    val scheduleList: List<ScheduleData>?,
    val subscribeCount: Int?
)