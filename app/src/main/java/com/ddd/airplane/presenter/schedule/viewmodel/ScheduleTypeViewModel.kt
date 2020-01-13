package com.ddd.airplane.presenter.schedule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.data.response.ScheduleData

class ScheduleTypeViewModel {

    private val _typeList = MutableLiveData<List<ScheduleData.Type>>()
    val typeList: LiveData<List<ScheduleData.Type>> = _typeList

    fun setData(list: List<ScheduleData.Type>) = apply {
        _typeList.value = list
    }
}
