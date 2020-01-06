package com.ddd.airplane.data.response

/**
 * 토큰발급, 로그인
 */
data class TokenResponse(
    val access_token: String?,
    val token_type: String?,
    val refresh_token: String?,
    val expires_in: String?,
    val scope: String?
)