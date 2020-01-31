package com.ddd.airplane.repository.database

import com.ddd.airplane.repository.database.member.MemberEntity
import com.ddd.airplane.repository.database.room.RoomManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * MemberRepository for Coroutine
 */
class MemberRepository : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val db = RoomManager.instance.memberDao()

    /**
     * 회원 정보 삭제
     */
    fun deleteAll() {
        CoroutineScope(coroutineContext).launch {
            db.deleteAll()
        }
    }

    /**
     * 회원 정보 세팅
     */
    fun insertMember(entity: MemberEntity) {
        CoroutineScope(coroutineContext).launch {
            db.memberInsert(entity)
        }
    }

    suspend fun select() =
        withContext(CoroutineScope(coroutineContext).coroutineContext) {
            db.select()
        }
}