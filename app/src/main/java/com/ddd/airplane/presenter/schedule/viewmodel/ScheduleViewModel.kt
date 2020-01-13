package com.ddd.airplane.presenter.schedule.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.data.response.ScheduleData

class ScheduleViewModel(application: Application) : BaseViewModel(application) {

    private val _scheduleList = MutableLiveData<ArrayList<ScheduleData>>()
    val scheduleList: LiveData<ArrayList<ScheduleData>> = _scheduleList

    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position

    init {
        getCategory()
    }

    /**
     * 스케줄 카테고리
     */
    private fun getCategory() {


        val typeList = ArrayList<ScheduleData.Type>()
        for (i in 1..5) {
            typeList.add(ScheduleData.Type("TYPE$i"))
        }

        val list = ArrayList<ScheduleData>()
        list.add(ScheduleData("방송별", typeList))
        list.add(ScheduleData("주제별", typeList))
        _scheduleList.value = list

    }

    /**
     * 현재 보고있는 페이지 Position
     */
    fun setCurrentPage(position: Int) {
        _position.value = position
    }
}
