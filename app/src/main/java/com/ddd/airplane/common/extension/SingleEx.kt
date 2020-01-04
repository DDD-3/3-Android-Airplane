package com.ddd.airplane.common.extension

import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.model.ErrorResponse
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

fun <T> Single<T>.request(
    listener: OnResponseListener<T>,
    isProgress: MutableLiveData<Boolean>? = null,
    isError: MutableLiveData<String>? = null
) {
    this
        .retry(3)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : SingleObserver<T> {

            override fun onSubscribe(d: Disposable) {
                Timber.v("onSubscribe($d) ${Thread.currentThread().name}")
                isProgress?.postValue(true)
            }

            override fun onSuccess(t: T) {
                Timber.v("onSuccess($t)")
                Timber.d("onSuccess(${Thread.currentThread().name})")

                // Return
                t?.let {
                    listener.onSuccess(t)
                } ?: listener.onError(
                    ErrorResponse("null", "Data is null")
                )

                isProgress?.postValue(false)
            }

            override fun onError(e: Throwable) {
                Timber.v("onError($e) ${Thread.currentThread().name}")

//                val errorBody = NetworkUtils.parseErrorBody(e)
//                Logger.d("Error Body : ${errorBody?.msg.plus("[-${errorBody?.code ?: 0}]")}")
//
//                listener?.onResultError(errorBody, contextProvider)
//                if (isShowLoading) contextProvider?.hideLoading()

                isProgress?.postValue(false)
                isError?.postValue("error message")

            }
        })

}