package com.ddd.airplane.repository.network.retrofit

import android.content.Context
import com.ddd.airplane.R
import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.manager.TokenManager
import com.ddd.airplane.repository.network.config.HttpStatus
import com.ddd.airplane.repository.network.retrofit.RequestManager.parseErrorResponse
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.data.response.home.BannerData
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.internal.Primitives
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import timber.log.Timber
import java.lang.reflect.Type

/**
 * 네트워크 통신
 */
fun <T> Single<T>.request(
    status: OnNetworkStatusListener? = null,
    listener: OnResponseListener<T>?
) {

    val context = status?.context

    this.retry(3)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : SingleObserver<T> {

            override fun onSubscribe(disposable: Disposable) {
                Timber.v("onSubscribe($disposable) ${Thread.currentThread().name}")
                status?.showProgress(true)
            }

            override fun onSuccess(response: T) {
                tryCatch {
                    Timber.v("onSuccess($response)")

                    // Return
                    response?.let {
                        listener?.onSuccess(response)
                    } ?: let {
                        // response 가 null 인 경우
                        val error = ErrorData(
                            error = "null",
                            error_description = "Data is null",
                            message = context?.getString(R.string.error_network_response_null) ?: ""
                        )

                        error.let {
                            status?.showToast(it.message)
                            listener?.onError(it)
                        }
                    }

                    status?.showProgress(false)
                }
            }

            override fun onError(e: Throwable) {
                tryCatch {
                    Timber.e("onError($e)")

                    val error = parseErrorResponse(context, e)
                    // 에러파싱 실패
                    error?.let {
                        if (error.status == HttpStatus.UNAUTHORIZED.code) {
                            // 토큰 재발급
                            TokenManager.onRefreshToken(status) { isRefresh ->
                                status?.showToast(
                                    context?.getString(
                                        if (isRefresh) R.string.error_network_response_retry
                                        else R.string.error_network_response_error
                                    )
                                )
                            }
                        } else {
                            status?.showToast(it.message)
                            listener?.onError(it)
                        }
                    }

                    status?.showProgress(false)
                }
            }
        })
}

object RequestManager {

    /**
     * 에러 바디 처리
     */
    fun parseErrorResponse(context: Context?, e: Throwable): ErrorData? {

        // 에러파싱 실패
        val error = ErrorData(
            status = 400,
            error = "error",
            error_description = "Network is error",
            message = context?.getString(R.string.error_network_response_error) ?: ""
        )

        try {
            val httpException = e as? HttpException ?: return error
//            val httpCode = httpException.code()
            val errorBody = httpException.response().errorBody() ?: return error
            val adapter = Gson().getAdapter(ErrorData::class.java)
            return adapter.fromJson(errorBody.string())
        } catch (e: Exception) {
            Timber.e(e)
        }

        return error
    }

    /**
     * ArrayList<LinkedTreeMap<String, Any>> 를 원하는 class 로 변환함
     *
     * @param T
     * @param list
     * @param classOfT
     * @return
     */
    fun <T> convertList(list: ArrayList<LinkedTreeMap<String, Any>>, classOfT: Class<T>): List<T>? {
        val convert = ArrayList<T>()
        list.forEach {
            val json = Gson().toJsonTree(it).asJsonObject
            val banner = Gson().fromJson(json, classOfT)
            convert.add(banner)
        }
        Timber.d(convert.toString())
        return convert
    }

    /**
     * ArrayList<LinkedTreeMap<String, Any>> 를 원하는 class 로 변환함
     *
     * @param T
     * @param data
     * @param classOfT
     * @return
     */
    fun <T> convertData(data: LinkedTreeMap<String, Any>, classOfT: Class<T>): T? {
        val json = Gson().toJsonTree(data).asJsonObject
        val model = Gson().fromJson(json, classOfT)
        Timber.d(model.toString())
        return model
    }

}