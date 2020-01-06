package com.ddd.airplane.data.request

/**
 * 토큰발급, 로그인
 */
data class AccessTokenRequest(
    val username: String,
    val password: String,
    val grant_type: String = "password"
)

/**
 * 토큰발급, 로그인
 */
data class TokenRefreshRequest(
    val refresh_token: String?,
    val grant_type: String = "refresh_token"
)