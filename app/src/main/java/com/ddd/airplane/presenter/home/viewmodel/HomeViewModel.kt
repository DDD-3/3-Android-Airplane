package com.ddd.airplane.presenter.home.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.consts.Home
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.data.response.chat.ProgramData
import com.ddd.airplane.data.response.home.BannerData
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.repository.network.retrofit.RequestManager
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import com.google.gson.internal.LinkedTreeMap

/**
 * 홈 ViewModel
 */
class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val _homeList = MutableLiveData<ArrayList<HomeData.ItemData>>()
    val homeList: LiveData<ArrayList<HomeData.ItemData>> = _homeList

    private val isExist: Boolean
        get() = _homeList.value?.size ?: 0 > 0

    /**
     * 홈 데이터
     */
    fun getHomeList() {

//        if (isExist) {
//            return
//        }
//
        RetrofitManager
            .general
            .getHomeList()
            .request(this, object : OnResponseListener<HomeData> {

                override fun onSuccess(response: HomeData) {
                    val list = response.list.also {
                        checkStyle(it)
                        checkItem(it)
                    }
                    _homeList.value = list
                }

                override fun onError(error: ErrorData) {

                }
            })
    }

    /**
     * 스타일 구하기
     *
     * @param list
     */
    private fun checkStyle(list: ArrayList<HomeData.ItemData>?) {
        list?.forEach { data ->
            Home.Style.values().forEach { homeStyle ->
                if (data.style == homeStyle.style) {
                    data.homeStyle = homeStyle
                }
            }
        }
    }

    /**
     * 아이템 체크
     *
     * @param list
     */
    private fun checkItem(list: ArrayList<HomeData.ItemData>?) {
        tryCatch {
            list?.forEach { data ->
                when (data.homeStyle) {
                    Home.Style.TOP_BANNER -> {
                        data.item = RequestManager.convertList(
                            data.item as ArrayList<LinkedTreeMap<String, Any>>,
                            BannerData::class.java
                        )
                    }

                    Home.Style.RECTANGLE_BANNER -> {
                        data.item = RequestManager.convertData(
                            data.item as LinkedTreeMap<String, Any>,
                            BannerData::class.java
                        )
                    }

                    Home.Style.HORIZONTAL, Home.Style.GRID, Home.Style.RANK -> {
                        data.item = RequestManager.convertList(
                            data.item as ArrayList<LinkedTreeMap<String, Any>>,
                            ProgramData::class.java
                        )
                    }

                    else -> {

                    }
                }
            }
        }
    }
}
