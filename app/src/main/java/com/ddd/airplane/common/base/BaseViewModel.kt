package com.ddd.airplane.common.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.data.response.ErrorData

abstract class BaseViewModel(application: Application) :
    AndroidViewModel(application),
    OnNetworkStatusListener {

    // progress
    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> = _isProgress

    // error
    private val _isErrorDialog = MutableLiveData<ErrorData>()
    val isErrorDialog: LiveData<ErrorData> = _isErrorDialog

    // toast
    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> = _toast

    override val context: Context
        get() = getApplication()

    /**
     * Progress
     *
     * @param isProgress
     */
    override fun showProgress(isProgress: Boolean) {
        _isProgress.postValue(isProgress)
    }

    /**
     * Error Dialog
     *
     * @param error
     */
    override fun showErrorDialog(error: ErrorData?) {

    }

    /**
     * Toast
     *
     * @param message
     */
    override fun showToast(message: String?) {
        message?.let {
            _toast.postValue(message)
        }
    }

}