package com.ddd.airplane.common.network.retrofit

import com.ddd.airplane.BuildConfig
import com.ddd.airplane.common.network.config.ServerInfo
import com.ddd.airplane.common.network.service.UserService
import io.reactivex.Flowable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * 레트로핏 매니저
 *
 * @author jess
 */
object RetrofitManager {

    val user: UserService by lazy {
        create(UserService::class.java, ServerInfo.DOMAIN.REAL.domain)
    }

    fun init() {

    }

    /**
     * Retrofit Service
     *
     * @param T
     * @param classes
     * @param baseUrl
     * @return
     */
    private fun <T> create(classes: Class<T>, baseUrl: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient())
            .build()
        return retrofit.create(classes)
    }

    /**
     * HTTP Client 생성
     */
    private fun okHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                getHeader(chain)
            }
            addInterceptor(logger())
        }
        return builder.build()
    }

    /**
     * Header
     *q
     * @param chain
     * @return
     */
    private fun getHeader(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "")
            .addHeader("VersionName", BuildConfig.VERSION_NAME)
            .addHeader("VersionCode", BuildConfig.VERSION_CODE.toString())
            .addHeader("ApplicationId", BuildConfig.APPLICATION_ID)
            .addHeader("IsDebug", BuildConfig.DEBUG.toString())
            .build()
        return chain.proceed(request)
    }

    /**
     * HTTP Logger
     */
    private fun logger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}