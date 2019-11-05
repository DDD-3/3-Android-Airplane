package com.ddd.airplane.common.network.retrofit

import com.ddd.airplane.BuildConfig
import com.ddd.airplane.common.network.config.ServerInfo
import com.ddd.airplane.common.network.service.TestService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    val test: TestService by lazy {
        create(TestService::class.java, ServerInfo.DOMAIN.REAL.domain)
    }

    fun init() {
        test
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
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient())
            .build()
        return retrofit.create(classes)
    }

    /**
     * HTTP Client 생성
     */
    private fun okHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor {
                header(it)
            }
            addInterceptor(logger())
        }
        return builder.build()
    }

    /**
     * Header
     *
     * @param chain
     * @return
     */
    private fun header(chain: Interceptor.Chain): Response? {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("key", "value")
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