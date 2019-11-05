package com.ddd.airplane.common.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

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
}