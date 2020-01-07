package com.ddd.airplane.common.repository.network.service

import com.ddd.airplane.data.request.*
import com.ddd.airplane.data.response.AccountResponse
import com.ddd.airplane.data.response.TokenResponse
import com.ddd.airplane.data.response.SignUpResponse
import io.reactivex.Single
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
    ): Single<SignUpResponse>

    /**
     * 계정조회
     */
    @GET("/api/v1/accounts/{email}")
    fun getAccounts(
        @Path("email") email: String
    ): Single<AccountResponse>

    /**
     * 토큰발급, 로그인
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    fun postAccessToken(
        @Body body: AccessTokenRequest
    ): Single<TokenResponse>

    /**
     * 토큰 재발급, 로그인
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    fun postTokenRefresh(
        @Body body: TokenRefreshRequest
    ): Single<TokenResponse>

}