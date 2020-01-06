package com.ddd.airplane.common.interfaces

import com.ddd.airplane.data.response.ErrorResponse

interface OnResponseListener<T> {

    fun onSuccess(response: T)

    fun onError(
        error: ErrorResponse
    )
}