package com.ddd.airplane.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    // progress
    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress = MutableLiveData<Boolean>()

}