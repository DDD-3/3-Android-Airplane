package com.ddd.airplane.common.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    companion object {

        lateinit var instance: AppDatabase

        fun init(context: Context) {
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
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