package com.ddd.airplane.repository.network.service

import retrofit2.Response
import retrofit2.http.*

/**
 * @author jess
 */
interface LikeService {

    /**
     * 좋아요 조회
     */
    @GET("/api/v1/subjects/{?}/like")
    suspend fun getLike(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Response<Any>

    /**
     * 좋아요
     */
    @FormUrlEncoded
    @POST("/api/v1/subjects/{?}/like")
    suspend fun postLike(

    ): Response<Unit>

    /**
     * 좋아요 취소
     */
    @DELETE("/api/v1/subjects/{?}/like")
    suspend fun deleteLike(

    ): Response<Unit>


}