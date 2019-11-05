package com.ddd.airplane.common.room.recent

import androidx.room.PrimaryKey

/**
 * 최근 본 방송
 *
 * @property id
 */
data class RecentEntity(
    @PrimaryKey var id: String
)