package com.ddd.airplane

import android.app.Application
import com.ddd.airplane.common.network.retrofit.RetrofitManager
import com.ddd.airplane.common.room.DatabaseManager
import timber.log.Timber

class AirPlaneApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        retrofit()
        timber()
        room()
    }

    override fun onTerminate() {
        DatabaseManager.close()
        super.onTerminate()
    }

    /**
     * Retrofit
     */
    private fun retrofit() {
        RetrofitManager.init()
    }

    /**
     * Timber
     */
    private fun timber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * Room
     */
    private fun room() {
        DatabaseManager.init(applicationContext)
    }
}
