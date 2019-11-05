package com.ddd.airplane

import android.app.Application
import com.ddd.airplane.common.room.AppDatabase
import timber.log.Timber

class AirPlaneApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        timber()
        room()
    }

    override fun onTerminate() {
        AppDatabase.close()
        super.onTerminate()
    }

    private fun timber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun room() {
        AppDatabase.init(applicationContext)
    }
}
