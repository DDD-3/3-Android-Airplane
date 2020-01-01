package com.ddd.airplane.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ddd.airplane.common.room.recent.RecentDao
import com.ddd.airplane.common.room.recent.RecentEntity

@Database(entities = [RecentEntity::class], version = 1)
abstract class DatabaseManager : RoomDatabase() {

    companion object {

        lateinit var instance: DatabaseManager

        fun init(context: Context) {
            instance = Room.databaseBuilder(
                context,
                DatabaseManager::class.java,
                "airplane.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        fun close() {
            instance.close()
        }
    }

    abstract fun recentDao(): RecentDao
}