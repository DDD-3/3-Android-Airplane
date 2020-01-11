package com.ddd.airplane.presenter.schedule.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.consts.Schedule

class ScheduleMainViewModel(application: Application) : BaseViewModel(application) {

    private val _category = MutableLiveData<ArrayList<Bundle>>()
    val category: LiveData<ArrayList<Bundle>> = _category

    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position

    init {
        getCategory()
    }

    /**
     * 스케줄 카테고리
     */
    private fun getCategory() {

        val bundleList = ArrayList<Bundle>()
        bundleList.run {

            // 방송사
            add(
                Bundle().apply {
                    putSerializable(Schedule.CATEGORY, "broadCast")
                }
            )

            // 주제
            add(
                Bundle().apply {
                    putSerializable(Schedule.CATEGORY, "subject")
                }
            )
        }

        _category.value = bundleList

    }

    /**
     * 현재 보고있는 페이지 Position
     */
    fun setCurrentPage(position: Int) {
        _position.value = position
    }
}
