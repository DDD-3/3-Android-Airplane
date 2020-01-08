package com.ddd.airplane.common.interfaces

import android.content.Context
import com.ddd.airplane.data.response.ErrorData

interface OnNetworkStatusListener {

    val context: Context

    fun showProgress(isProgress: Boolean)

    fun showErrorDialog(error: ErrorData?)

    fun showToast(message: String?)

}