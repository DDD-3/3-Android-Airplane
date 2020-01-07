package com.ddd.airplane.common.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ddd.airplane.common.repository.database.member.MemberDao
import com.ddd.airplane.common.repository.database.member.MemberEntity

@Database(entities = [MemberEntity::class], version = 1)
abstract class RoomManager : RoomDatabase() {

    companion object {

        lateinit var instance: RoomManager

        fun init(context: Context) {
            instance = Room.databaseBuilder(
                context,
                RoomManager::class.java,
                "airplane.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        fun close() {
            instance.close()
        }
    }

    abstract fun memberDao(): MemberDao
}