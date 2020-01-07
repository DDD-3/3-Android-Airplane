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
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * 회원정보 관리
 */
object MemberManager {

    private val memberDao = RoomManager.instance.memberDao()

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
     * 로그아웃
     */
    fun signOut(context: Context, listener: (() -> Unit)) {
        TokenManager.removeToken()
        removeAccount()
        listener.invoke()
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
            .observeOn(AndroidSchedulers.mainThread())
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

                    val tasks = listOf(
                        delete,
                        insert
                    )

                    Observable
                        .fromIterable(tasks)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            listener?.invoke(true)
                        }, {
                            listener?.invoke(false)
                        })

                    sigInInListener = null
                }

                override fun onError(error: ErrorResponse) {
                    listener?.invoke(false)
                }
            })
    }
}