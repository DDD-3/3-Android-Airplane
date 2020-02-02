package com.ddd.airplane.repository.network.service

import com.ddd.airplane.data.request.*
import com.ddd.airplane.data.response.user.AccountData
import com.ddd.airplane.data.response.user.TokenData
import com.ddd.airplane.data.response.user.SignUpData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * @author jess
 */
interface UserService {

    /**
     * 계정생성
     */
    @POST("/api/v1/accounts")
    fun postAccounts(
        @Body body: SignUpRequest
    ): Single<SignUpData>

    /**
     * 계정조회
     */
    @GET("/api/v1/accounts/{email}")
    fun getAccounts(
        @Path("email") email: String
    ): Single<AccountData>

    /**
     * 토큰발급
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    fun postAccessToken(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String
    ): Single<TokenData>

    /**
     * 토큰 재발급
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    fun postTokenRefresh(
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String
    ): Single<TokenData>

    /**
     * 토큰 재발급
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    suspend fun postTokenRefreshCoruoutine(
        @Field("refresh_token") refreshToken: String?,
        @Field("grant_type") grantType: String
    ): Response<TokenData>

}