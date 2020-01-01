package com.ddd.airplane.common.room.recent

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 최근 본 방송
 *
 * @property id
 */
@Entity(tableName = "RecentEntity")
data class RecentEntity(
    @PrimaryKey var id: String
)