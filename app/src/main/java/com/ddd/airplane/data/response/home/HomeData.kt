package com.ddd.airplane.data.response.home

/**
 * 홈 데이터
 */
sealed class HomeData {

    data class SwipeData(val test: String) : HomeData()

}