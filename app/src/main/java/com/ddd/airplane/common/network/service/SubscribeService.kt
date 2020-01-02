package com.ddd.airplane.common.network.service

import io.reactivex.Single
import retrofit2.http.*

/**
 * @author jess
 */
interface SubscribeService {

    /**
     * 구독한 채팅방 조회
     * TODO
     */
    @GET("/api/v1/roomsOfSubscribedSubjects")
    fun getSubscribe(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Single<Any>

    /**
     * 구독
     * TODO
     */
    @FormUrlEncoded
    @POST("/api/v1/subjects/{?}/subscribe")
    fun postSubscribe(

    ): Single<Any>

    /**
     * 구독 취소
     * TODO
     */
    @DELETE("/api/v1/subjects/{?}/subscribe")
    fun deleteSubscribe(

    ): Single<Any>


}