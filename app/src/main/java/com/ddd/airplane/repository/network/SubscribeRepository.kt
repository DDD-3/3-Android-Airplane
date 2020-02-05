package com.ddd.airplane.repository.network

import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * GeneralRepository for Coroutine
 */
class SubscribeRepository(
    private val status: OnNetworkStatusListener? = null,
    private val scope: CoroutineScope
) {

    private val service = RetrofitManager.subscribe

    init {
        scope.launch(Dispatchers.Main) {
            status?.showProgress(true)
        }
    }

    /**
     * 구독 방송 리스트
     * @param pageNum
     */
    suspend fun getSubscribe(pageNum: Int = 1) =
        service.getSubscribe(pageNum).request(status)
}