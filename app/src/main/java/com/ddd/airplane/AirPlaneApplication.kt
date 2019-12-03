package com.ddd.airplane

import android.app.Application
import com.ddd.airplane.common.network.retrofit.RetrofitManager
import com.ddd.airplane.common.room.DatabaseManager
import com.ddd.airplane.pages.signin.KakaoSdkAdapter
import com.kakao.auth.KakaoSDK
import timber.log.Timber

/**
 * @author jess
 */
class AirPlaneApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        retrofit()
        timber()
        room()
        kakao()
    }

    override fun onTerminate() {
        DatabaseManager.close()
        instance = null
        super.onTerminate()
    }

    companion object {
        var instance: AirPlaneApplication? = null
    }

    fun getGlobalApplicationContext() : AirPlaneApplication {
        checkNotNull(instance) {"this application does not inherit"}
        return instance!!
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

    /**
     * Kakao
     */
    private fun kakao() {
        KakaoSDK.init(KakaoSdkAdapter())
    }
}
