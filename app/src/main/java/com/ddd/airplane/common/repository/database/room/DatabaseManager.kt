package com.ddd.airplane.common.repository.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(version = 1)
//abstract class DatabaseManager : RoomDatabase() {
//
//    companion object {
//
//        lateinit var instance: DatabaseManager
//
//        fun init(context: Context) {
//            instance = Room.databaseBuilder(
//                context,
//                DatabaseManager::class.java,
//                "airplane.db"
//            )
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//
//        fun close() {
//            instance.close()
//        }
//    }
//}