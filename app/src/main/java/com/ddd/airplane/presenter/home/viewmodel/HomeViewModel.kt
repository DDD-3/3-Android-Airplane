package com.ddd.airplane.presenter.home.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.consts.Home
import com.ddd.airplane.data.response.home.HomeData

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val _homeList = MutableLiveData<ArrayList<HomeData>>()
    val homeList: LiveData<ArrayList<HomeData>> = _homeList

    /**
     * 홈 데이터
     */
    fun getHomeList() {

        val homeList = ArrayList<HomeData>()

        // swipe
        val swipeBanner = ArrayList<HomeData.SwipeData>()
        // 상단 배너
        for (i in 1..10) {
            swipeBanner.add(HomeData.SwipeData("banner test $i"))
        }
//        homeList.add(HomeData(Home.Type.SWIPE_BANNER, swipeBanner))

        _homeList.value = homeList
    }

//    /**
//     * 아이템 타입
//     *
//     * @param position
//     */
//    fun getItemViewType(position: Int) = _homeList.value?.get(position)?.type?.ordinal ?: 99

}
