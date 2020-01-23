package com.ddd.airplane.presenter.home.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.consts.Home
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.data.response.home.BannerData

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val _homeList = MutableLiveData<ArrayList<HomeData.ItemData<Any>>>()
    val homeList: LiveData<ArrayList<HomeData.ItemData<Any>>> = _homeList

    /**
     * 홈 데이터
     */
    fun getHomeList() {

        // 홈 리스트
        val itemList = ArrayList<HomeData.ItemData<Any>>()

        // swipe
        val swipeBanner = ArrayList<BannerData>().apply {
            add(BannerData("혼자 방송 볼 때\n심심하다면?\n사바사!", R.drawable.img_main_banner_01))
            add(BannerData("사이더들과\n오늘 밤\n달려요!", R.drawable.img_main_banner_02))
            add(BannerData("구독 방송\n핫한 방송\n한번에!", R.drawable.img_main_banner_03))
        }



        itemList.add(
            HomeData.ItemData(
                Home.Type.SWIPE_BANNER, swipeBanner
            )
        )

        _homeList.value = itemList
    }
}
