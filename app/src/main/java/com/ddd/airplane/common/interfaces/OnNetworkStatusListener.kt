package com.ddd.airplane.common.interfaces

import android.content.Context
import com.ddd.airplane.data.response.ErrorResponse

interface OnNetworkStatusListener {

    fun getContext(): Context

    fun showProgress(isProgress: Boolean)

    fun showErrorDialog(error: ErrorResponse?)

    fun showToast(message: String?)
}