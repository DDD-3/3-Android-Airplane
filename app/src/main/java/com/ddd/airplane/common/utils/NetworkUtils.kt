package com.ddd.airplane.common.utils

import com.ddd.airplane.common.repository.network.config.HttpStatus
import com.ddd.airplane.data.response.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.lang.Exception

object NetworkUtils {

    fun getErrorResponse(e: Throwable): ErrorResponse? {
        try {

            val httpException = e as? HttpException ?: return null
            val httpCode = httpException.code()
            val errorBody = httpException.response().errorBody() ?: return null

            if (httpCode == HttpStatus.UNAUTHORIZED.code) {
                // 토큰 재발급
            }

            val adapter = Gson().getAdapter(ErrorResponse::class.java)
            return adapter.fromJson(errorBody.string())

        } catch (e: Exception) {
            Timber.e(e)
            return null
        }
    }
}