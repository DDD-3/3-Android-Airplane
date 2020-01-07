package com.ddd.airplane.common.manager

import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.repository.database.member.MemberEntity
import com.ddd.airplane.common.repository.database.room.RoomManager
import com.ddd.airplane.common.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.common.repository.network.retrofit.request
import com.ddd.airplane.data.response.AccountResponse
import com.ddd.airplane.data.response.ErrorResponse

/**
 * 회원정보 관리
 */
object MemberManager {

    /**
     * 회원정보 조회
     */
    fun getAccount(
        status: OnNetworkStatusListener,
        email: String,
        listener: ((Boolean) -> Unit)? = null
    ) {
        RetrofitManager
            .user
            .getAccounts(email)
            .request(status, object : OnResponseListener<AccountResponse> {

                override fun onSuccess(response: AccountResponse) {

                    RoomManager.instance
                        .memberDao().insert(
                            MemberEntity(
                                response.email ?: "",
                                response.nickname ?: ""
                            )
                        )

                    listener?.invoke(true)
                }

                override fun onError(error: ErrorResponse) {
                    listener?.invoke(false)
                }
            })
    }

    /**
     * 회원정보 삭제
     */
    fun removeAccount() {
        RoomManager.instance.memberDao().deleteAll()
    }

}