package com.ddd.airplane.repository.network.service

import com.ddd.airplane.data.response.home.HomeData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author jess
 */
interface GeneralService {

    /**
     * 홈 리스트 조회
     */
    @GET("/api/v1/home")
    fun getHomeList(): Single<HomeData>

    /**
     * 검색
     */
    @GET("/api/v1/home")
    fun getSearchList(): Response<HomeData>

}