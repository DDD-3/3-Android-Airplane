package com.ddd.airplane.repository.database

import com.ddd.airplane.repository.database.recent.RecentEntity
import com.ddd.airplane.repository.database.room.RoomManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * RecentRepository for Coroutine
 */
class RecentRepository : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val db = RoomManager.instance.recentDao()

    /**
     * 최근 본 방송
     */
    suspend fun selectTopLimit() =
        withContext(CoroutineScope(coroutineContext).coroutineContext) {
            db.selectTopLimit()
        }

    /**
     * 최근 본 방송 삽입
     */
    fun insertRecent(entity: RecentEntity) {
        CoroutineScope(coroutineContext).launch {
            db.insert(entity)
        }
    }
}