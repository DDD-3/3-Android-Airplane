package com.ddd.airplane.repository.network

import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request

/**
 * GeneralRepository for Coroutine
 */
class GeneralRepository(
    private val status: OnNetworkStatusListener? = null
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