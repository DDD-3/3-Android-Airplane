package com.ddd.airplane.common.manager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.ddd.airplane.common.interfaces.OnNetworkStatusListener
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.repository.database.member.MemberEntity
import com.ddd.airplane.repository.database.room.RoomManager
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import com.ddd.airplane.data.response.user.AccountData
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.presenter.signin.view.SignInActivity
import com.ddd.airplane.repository.database.MemberRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.Exception

/**
 * 회원정보 관리
 */
object MemberManager {

    private val memberDao = RoomManager.instance.memberDao()

    // 로그인 리스너
    private var sigInInListener: ((Boolean) -> Unit)? = null

    private val repository = MemberRepository()

    /**
     * 로그인
     */
    fun signIn(context: Context?, sigInInListener: ((Boolean) -> Unit)? = null) {
        this.sigInInListener = sigInInListener
        Intent(context, SignInActivity::class.java).let {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context?.startActivity(it)
        }
    }

    /**
     * 로그인 결과
     */
    fun signInResult(signIn: Boolean) {
        // listener 있으면 리턴 없으면 postValue
        sigInInListener?.invoke(signIn)
        sigInInListener = null
    }

    /**
     * 로그아웃
     */
    fun signOut(listener: (() -> Unit)? = null) {
        TokenManager.removeToken()
        removeAccount()
        listener?.invoke()
    }

    /**
     * 계정정보 삭제
     *
     */
    private fun removeAccount() {
        repository.deleteAll()
    }


    /**
     * 계정정보
     */
    suspend fun getAccount(listener: ((MemberEntity?) -> Unit)?) {
        listener?.invoke(repository.select())
    }

    /**
     * 계정정보 저장
     */
    fun setAccount(
        status: OnNetworkStatusListener,
        email: String,
        listener: ((Boolean) -> Unit)? = null
    ) {
        RetrofitManager
            .user
            .getAccounts(email)
            .request(status, object : OnResponseListener<AccountData> {

                override fun onSuccess(response: AccountData) {
                    try {
                        MemberRepository().insertMember(
                            MemberEntity(
                                response.email ?: "",
                                response.nickname ?: ""
                            )
                        )
                        listener?.invoke(true)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        listener?.invoke(false)
                    }
                }

                override fun onError(error: ErrorData) {
                    listener?.invoke(false)
                }
            })
    }
}