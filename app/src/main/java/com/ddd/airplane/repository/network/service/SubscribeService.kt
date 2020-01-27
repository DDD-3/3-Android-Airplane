package com.ddd.airplane.repository.network.service

import com.ddd.airplane.data.response.SubscribeData
import io.reactivex.Single
import retrofit2.http.*

/**
 * @author jess
 */
interface SubscribeService {

    /**
     * 구독한 채팅방 조회
     */
    @GET("/api/v1/roomsOfSubscribedSubjects")
    fun getSubscribe(
        @Query("pageNum") pageNum: Int
    ): Single<SubscribeData>

    /**
     * 구독
     */
    @FormUrlEncoded
    @POST("/api/v1/subjects/{subjectId}/subscribe")
    fun postSubscribe(
        @Path("subjectId") subjectId: Long,
        @Field("subjectId") subId: Long
    ): Single<Any>

    /**
     * 구독 취소
     */
    @DELETE("/api/v1/subjects/{subjectId}/subscribe")
    fun deleteSubscribe(
        @Path("subjectId") subjectId: Long
    ): Single<Any>


}