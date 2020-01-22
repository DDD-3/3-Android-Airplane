package com.ddd.airplane.data.response.home

import com.ddd.airplane.common.consts.Home

/**
 * 홈 데이터
 */
open class HomeData() {

    var type: Home.Type = Home.Type.EMPTY
    var banner: Any? = null

    constructor(
        type: Home.Type,
        banner: Any
    ) : this() {
        this.type = type
        this.banner = banner
    }

}