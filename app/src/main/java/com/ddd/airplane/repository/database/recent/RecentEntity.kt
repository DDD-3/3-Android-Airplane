package com.ddd.airplane.repository.database.recent

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 최근 본 프로그램
 *
 * @property roomId
 * @property subjectName
 * @property subjectDescription
 * @property subjectThumbnailUrl
 */
@Entity(tableName = "RecentEntity")
data class RecentEntity(
    @PrimaryKey
    val roomId: Long? = 0,
    val subjectName: String? = null,
    val subjectDescription: String? = null,
    val subjectThumbnailUrl: String? = null
)