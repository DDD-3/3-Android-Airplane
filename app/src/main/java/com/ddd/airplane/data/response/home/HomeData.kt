package com.ddd.airplane.data.response.home

import com.ddd.airplane.common.consts.Home

/**
 * 홈 데이터
 */
data class HomeData(
    var list: ArrayList<ItemData<Any>>? = null
) {

    data class ItemData<T>(
        var type: Home.Type = Home.Type.EMPTY,
        var item: T? = null
    )
}
