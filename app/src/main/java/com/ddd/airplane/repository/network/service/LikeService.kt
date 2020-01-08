package com.ddd.airplane.repository.network.service

import io.reactivex.Single
import retrofit2.http.*

/**
 * @author jess
 */
interface LikeService {

    /**
     * 좋아요 조회
     * TODO
     */
    @GET("/api/v1/subjects/{?}/like")
    fun getLike(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Single<Any>

    /**
     * 좋아요
     * TODO
     */
    @FormUrlEncoded
    @POST("/api/v1/subjects/{?}/like")
    fun postLike(

    ): Single<Any>

    /**
     * 구독 취소
     * TODO
     */
    @DELETE("/api/v1/subjects/{?}/like")
    fun deleteLike(

    ): Single<Any>


}