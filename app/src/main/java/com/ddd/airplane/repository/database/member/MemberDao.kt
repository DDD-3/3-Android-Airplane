package com.ddd.airplane.repository.database.member

import androidx.room.Dao
import androidx.room.Query
import com.ddd.airplane.common.base.BaseDao
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MemberDao : BaseDao<MemberEntity> {

    @Query("SELECT * FROM MemberEntity")
    fun select(): Single<MemberEntity>

    @Query("DELETE FROM MemberEntity")
    fun deleteAll(): Completable

}