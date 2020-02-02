package com.ddd.airplane.repository.network

import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.manager.TokenManager
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

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
     * 토큰 새로고침
     */
    suspend fun postTokenRefresh(refreshToken: String?) =
        service.postTokenRefreshCoruoutine(refreshToken, TokenManager.REFRESH_TOKEN).request(status)
}