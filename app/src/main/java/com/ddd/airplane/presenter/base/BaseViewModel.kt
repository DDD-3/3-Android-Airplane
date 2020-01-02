package com.ddd.airplane.presenter.base

import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel {

    // progress
    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress = MutableLiveData<Boolean>()

}