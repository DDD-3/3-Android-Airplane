package com.ddd.airplane.data.response.home

import com.ddd.airplane.common.consts.Home

/**
 * 홈 데이터
 */
data class HomeData(
    var list: ArrayList<ItemData<Any>>? = null
) {

    data class ItemData<T>(
        var style: Home.Style = Home.Style.EMPTY,
        var item: T? = null
    )
}
