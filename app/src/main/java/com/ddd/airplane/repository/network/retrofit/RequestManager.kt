package com.ddd.airplane.repository.network.retrofit

import android.content.Context
import com.ddd.airplane.R
import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.manager.TokenManager
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.repository.network.config.HttpStatus
import com.ddd.airplane.repository.network.retrofit.RequestManager.parseErrorBody
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

/**
 * 네트워크 통신
 */
fun <T> Response<T>?.request(
    status: OnNetworkStatusListener? = null,
    listener: OnResponseListener<T>? = null
): T? {
    val context = status?.context
    this?.let { response ->
        if (!response.isSuccessful) {
            val error = parseErrorBody(context, response.errorBody())
            status?.showToast(error.message)
            RequestManager.onError(
                error, status, listener
            )
        }
    }
    status?.showProgress(false)
    return this?.body()
}

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
                    }
//                        ?: let {
//                        // response 가 null 인 경우
//                        val error = ErrorData(
//                            error = "null",
//                            error_description = "Data is null",
//                            message = context?.getString(R.string.error_network_response_null) ?: ""
//                        )
//
//                        error.let {
//                            status?.showToast(it.message)
//                            listener?.onError(it)
//                        }
//                    }

                    status?.showProgress(false)
                }
            }

            override fun onError(e: Throwable) {
                tryCatch {
                    Timber.e("onError($e)")

                    val errorBody = (e as HttpException).response()?.errorBody()
                    val error = parseErrorBody(context, errorBody)

                    RequestManager.onError(
                        error, status, listener
                    )

                    status?.showProgress(false)
                }
            }
        })
}

object RequestManager {

    /**
     * 에러 바디 처리
     */
    fun parseErrorBody(context: Context?, errorBody: ResponseBody?): ErrorData {

        // 에러파싱 실패
        val error = ErrorData(
            status = 400,
            error = "error",
            error_description = "Network is error",
            message = context?.getString(R.string.error_network_response_error) ?: ""
        )

        errorBody?.let {
            try {
                val adapter = Gson().getAdapter(ErrorData::class.java)
                return adapter.fromJson(it.string())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

        return error
    }

    fun <T> onError(
        error: ErrorData,
        status: OnNetworkStatusListener? = null,
        listener: OnResponseListener<T>? = null
    ) {
        val context = status?.context

        // 에러파싱 실패
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
            status?.showToast(error.message)
            listener?.onError(error)
        }
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