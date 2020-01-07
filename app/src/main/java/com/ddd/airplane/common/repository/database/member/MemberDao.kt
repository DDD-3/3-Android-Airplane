package com.ddd.airplane.common.repository.database.member

import androidx.room.Dao
import androidx.room.Query
import com.ddd.airplane.common.base.BaseDao
import io.reactivex.Single

@Dao
interface MemberDao : BaseDao<MemberEntity> {

    @Query("SELECT * FROM MemberEntity LIMIT 1")
    fun select(): Single<MemberEntity>

    @Query("DELETE FROM MemberEntity")
    fun deleteAll()

}