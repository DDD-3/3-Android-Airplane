package com.ddd.airplane.repository.database.recent

import androidx.room.Dao
import androidx.room.Query
import com.ddd.airplane.common.base.BaseDao
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RecentDao : BaseDao<RecentEntity> {

    @Query("SELECT * FROM RecentEntity")
    suspend fun select(): List<RecentEntity>

    @Query("SELECT * FROM RecentEntity LIMIT 1")
    suspend fun selectToplimit(): RecentEntity

}