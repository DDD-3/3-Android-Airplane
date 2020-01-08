package com.ddd.airplane.common.manager

import android.annotation.SuppressLint
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * 회원정보 관리
 */
object MemberManager {

    private val memberDao = RoomManager.instance.memberDao()

    // 로그인 리스너
    private var sigInInListener: ((Boolean) -> Unit)? = null

    /**
     * 로그인
     */
    fun signIn(context: Context?, sigInInListener: ((Boolean) -> Unit)? = null) {
        this.sigInInListener = sigInInListener
        context?.startActivity(Intent(context, SignInActivity::class.java))
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
    @SuppressLint("CheckResult")
    fun removeAccount() {
        memberDao
            .deleteAll()
            .subscribeOn(Schedulers.io())
    }


    /**
     * 계정정보
     */
    @SuppressLint("CheckResult")
    fun getAccount(listener: ((MemberEntity?) -> Unit)? = null) {
        memberDao
            .select()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(it.toString())
                listener?.invoke(it)
            }, {
                Timber.e(it)
                listener?.invoke(null)
            })
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
            .request(status, object : OnResponseListener<AccountResponse> {

                @SuppressLint("CheckResult")
                override fun onSuccess(response: AccountResponse) {

                    // 기존 정보 지우고 새로 삽입
                    val delete = memberDao.deleteAll()
                    val insert = memberDao.insert(
                        MemberEntity(
                            response.email ?: "",
                            response.nickname ?: ""
                        )
                    )

                    delete
                        .concatWith(insert)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            listener?.invoke(true)
                        }, {
                            Timber.e(it)
                            listener?.invoke(false)
                        })
                }

                override fun onError(error: ErrorResponse) {
                    listener?.invoke(false)
                }
            })
    }
}