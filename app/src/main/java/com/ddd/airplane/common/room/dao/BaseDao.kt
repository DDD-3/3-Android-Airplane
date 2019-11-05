package com.ddd.airplane.common.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable

/**
 * Room BaseDAO
 * @author jess
 */
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Completable

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj: T): Completable

    @Delete
    fun delete(obj: T): Completable

}

