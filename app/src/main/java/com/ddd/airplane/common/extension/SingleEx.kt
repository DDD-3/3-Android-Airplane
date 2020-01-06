package com.ddd.airplane.common.extension

import com.ddd.airplane.R
import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.utils.NetworkUtils
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.ErrorResponse
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Single
 * @param status
 * @param listener
 */
fun <T> Single<T>.request(
    status: OnNetworkStatusListener? = null,
    listener: OnResponseListener<T>?
) {

    val context = status?.getContext()

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
                        val error = ErrorResponse(
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

                    NetworkUtils.getErrorResponse(e)?.let {
                        // 네트워크에서 전송한 에러 메세지
                        listener?.onError(it)
                        status?.showToast(it.message)
                    } ?: let {
                        // 에러파싱 실패
                        val error = ErrorResponse(
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
        })

}