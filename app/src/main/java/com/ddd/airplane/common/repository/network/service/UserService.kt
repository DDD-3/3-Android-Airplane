package com.ddd.airplane.common.repository.network.service

import com.ddd.airplane.data.request.RefreshTokenRequest
import com.ddd.airplane.data.request.SignInTokenRequest
import com.ddd.airplane.data.response.TokenResponse
import com.ddd.airplane.data.request.SignUpRequest
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
    ): Single<Any>

    /**
     * 토큰발급, 로그인
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    fun postSignInToken(
        @Body body: SignInTokenRequest
    ): Single<TokenResponse>

    /**
     * 토큰발급, 로그인
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    fun postRefreshToken(
        @Body body: RefreshTokenRequest
    ): Single<TokenResponse>

}