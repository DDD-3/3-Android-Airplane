package com.ddd.airplane.repository.network.service

import com.ddd.airplane.data.response.SearchData
import com.ddd.airplane.data.response.home.HomeData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author jess
 */
interface GeneralService {

    /**
     * 홈 리스트 조회
     */
    @GET("/api/v1/home")
    fun getHome(): Single<HomeData>

    /**
     * 검색
     */
    @GET("/api/v1/search")
    fun getSearch(
        @Query("query") query: String,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Response<SearchData>

}