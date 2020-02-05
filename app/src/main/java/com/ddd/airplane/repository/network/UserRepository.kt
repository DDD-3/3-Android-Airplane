package com.ddd.airplane.repository.network

import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.manager.TokenManager
import com.ddd.airplane.data.request.SignUpRequest
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * UserRepository for Coroutine
 */
class UserRepository(
    private val status: OnNetworkStatusListener? = null,
    private val scope: CoroutineScope
) {

    private val service = RetrofitManager.user

    init {
        scope.launch(Dispatchers.Main) {
            status?.showProgress(true)
        }
    }

    /**
     * 계정생성
     */
    suspend fun postAccounts(signUpRequest: SignUpRequest) =
        service.postAccounts(signUpRequest).request(status)

    /**
     * 토큰 새로고침
     */
    suspend fun postTokenRefresh(refreshToken: String?) =
        service.postTokenRefreshCoruoutine(refreshToken, TokenManager.REFRESH_TOKEN).request(status)
}