package com.ddd.airplane.common.manager

import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.common.repository.network.retrofit.request
import com.ddd.airplane.data.request.AccessTokenRequest
import com.ddd.airplane.data.request.TokenRefreshRequest
import com.ddd.airplane.data.response.ErrorResponse
import com.ddd.airplane.data.response.TokenResponse
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
    fun set(accessToken: String?, refreshToken: String?, tokenType: String?) {
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


    /**
     * 토큰발급
     */
    fun getAccessToken(
        status: OnNetworkStatusListener?,
        email: String,
        password: String,
        listener: ((Boolean) -> Unit)? = null
    ) {
        RetrofitManager
            .user
            .postAccessToken(AccessTokenRequest(email, password))
            .request(status, object : OnResponseListener<TokenResponse> {

                override fun onSuccess(response: TokenResponse) {
                    response.run {
                        set(accessToken, refreshToken, tokenType)
                    }
                    listener?.invoke(true)
                }

                override fun onError(error: ErrorResponse) {
                    listener?.invoke(false)
                }
            })
    }

    /**
     * 토큰 재발급
     */
    fun onTokenRefresh(
        status: OnNetworkStatusListener?,
        listener: ((Boolean) -> Unit)? = null
    ) {

        if (refreshToken.isNullOrEmpty()) {
            listener?.invoke(false)
            return
        }

        RetrofitManager
            .user
            .postTokenRefresh(TokenRefreshRequest(refreshToken))
            .request(status, object : OnResponseListener<TokenResponse> {

                override fun onSuccess(response: TokenResponse) {
                    response.run {
                        remove()
                        set(accessToken, refreshToken, tokenType)
                    }
                    listener?.invoke(true)
                }

                override fun onError(error: ErrorResponse) {
                    listener?.invoke(false)
                }
            })
    }


}