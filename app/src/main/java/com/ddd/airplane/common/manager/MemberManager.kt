package com.ddd.airplane.common.manager

import android.content.Context
import android.content.Intent
import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.repository.database.member.MemberEntity
import com.ddd.airplane.common.repository.database.RoomManager
import com.ddd.airplane.common.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.common.repository.network.retrofit.request
import com.ddd.airplane.data.response.AccountResponse
import com.ddd.airplane.data.response.ErrorResponse
import com.ddd.airplane.presenter.signin.view.SignInActivity

/**
 * 회원정보 관리
 */
object MemberManager {

    // 로그인 리스너
    var sigInInListener: ((Boolean) -> Unit)? = null

    /**
     * 로그인
     */
    fun signIn(context: Context, sigInInListener: ((Boolean) -> Unit)) {
        this.sigInInListener = sigInInListener
        context.startActivity(Intent(context, SignInActivity::class.java))
    }

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