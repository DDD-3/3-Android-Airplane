package com.ddd.airplane.presenter.splash.viewmodel

import android.app.Application
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.common.manager.TokenManager

class SplashViewModel(application: Application) : BaseViewModel(application) {

    private var listener: ((Boolean) -> Unit)? = null

    /**
     * Init Flow
     *
     * @param listener
     */
    fun doInitFlow(listener: ((Boolean) -> Unit)) {
        this.listener = listener
        // 토큰갱신
        refreshToken()
    }

    /**
     * 토큰갱신
     */
    private fun refreshToken() {
        if (TokenManager.isExist()) {
            // 로그인 유저
            TokenManager.onRefreshToken(this) { isRefresh ->
                if (!isRefresh) {
                    // 토큰 갱신 실패시 로그아웃 처리
                    MemberManager.signOut()
                }
                listener?.invoke(true)
            }
        } else {
            listener?.invoke(false)
        }
    }
}