package com.ddd.airplane.common.interfaces

import com.ddd.airplane.common.model.ErrorResponse

interface OnResponseListener<T> {

    fun onSuccess(t: T)

    fun onError(
        error: ErrorResponse?
    ) {
//        if (error?.code == NetworkConsts.ECODE_SERVER_INSPECT) {
//            // 시스템 점검일 경우 앱을 종료한다.
//            contextProvider?.showNetworkInspectDialog(error.msg)
//            return
//        }
//
//        contextProvider?.showNetworkErrorDialog(error?.msg)
    }
}