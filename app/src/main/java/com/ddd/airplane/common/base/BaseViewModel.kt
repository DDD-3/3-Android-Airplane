package com.ddd.airplane.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    // progress
    protected val isProgress = MutableLiveData<Boolean>() // Dialog 보임 여부

    // error
    protected val isError = MutableLiveData<String>() // 에러 메세지

}