package com.ddd.airplane.common.manager

import timber.log.Timber

/**
 * 토큰 관리
 */
object TokenManager {

    private const val ACCESS_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"
    private const val TOKEN_TYPE = "token_type"

    /**
     * Set Token
     */
    fun set(accessToken: String, refreshToken: String, tokenType: String) {
        PreferencesManager.run {
            putValue(ACCESS_TOKEN, accessToken)
            putValue(REFRESH_TOKEN, refreshToken)
            putValue(TOKEN_TYPE, tokenType)
        }
    }

    /**
     * Remove
     *
     */
    fun remove() {
        PreferencesManager.run {
            remove(ACCESS_TOKEN)
            remove(REFRESH_TOKEN)
            remove(TOKEN_TYPE)
        }
    }

    /**
     * Access Token
     */
    val accessToken: String?
        get() {
            PreferencesManager.getString(ACCESS_TOKEN).let {
                Timber.d("Access Token : $it")
                return it
            }
        }

    /**
     * Access Token
     */
    val refreshToken: String?
        get() {
            PreferencesManager.getString(REFRESH_TOKEN).let {
                Timber.d("Refresh Token : $it")
                return it
            }
        }

    /**
     * Token Type
     * Authorization 에서 사용
     */
    val tokenType: String?
        get() {
            PreferencesManager.getString(TOKEN_TYPE).let {
                Timber.d("Token Type : $it")
                return it
            }
        }



}