package com.ddd.airplane.common.network.service

import io.reactivex.Single
import retrofit2.http.*

/**
 * @author jess
 */
interface UserService {

    /**
     * 계정생성
     */
    @FormUrlEncoded
    @POST("/api/v1/accounts")
    fun postAccounts(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("nickname") nickname: String
    ): Single<Any>

    /**
     * 토큰발급 (로그인)
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    fun postToken(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("nickname") nickname: String = "grant_type"
    ): Single<Any>

    /**
     * 계정조회
     */
    @GET("/api/v1/accounts/{email}")
    fun getAccounts(
        @Path("email") email: String
    ): Single<Any>


}