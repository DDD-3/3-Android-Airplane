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
class GeneralRepository(
    private val status: OnNetworkStatusListener? = null,
    private val coroutineScope: CoroutineScope
) {

    private val service = RetrofitManager.general

    init {
        status?.showProgress(true)
    }

    /**
     * 검색
     *
     * @param query
     * @param pageNum
     */
    suspend fun getSearch(query: String, pageNum: Int = 0) =
        service.getSearch(query, pageNum).request(status)
}