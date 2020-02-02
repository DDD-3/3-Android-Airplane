package com.ddd.airplane.presenter.splash.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.common.manager.TokenManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashViewModel(application: Application) : BaseViewModel(application) {

    private var listener: ((Boolean) -> Unit)? = null

    /**
     * Init Flow
     *
     * @param listener
     */
    fun doInitFlow(listener: ((Boolean) -> Unit)) {
        this.listener = listener
        viewModelScope.launch(ioDispatchers) {

            delay(1000)

            // 토큰갱신
            Timber.d(">> 토큰갱신 시작")
            refreshToken()
            Timber.d(">> 스플래시 flow 종료")

            listener.invoke(TokenManager.isExist())
        }
    }

    /**
     * 토큰갱신
     */
    private suspend fun refreshToken() {
        TokenManager.onRefreshTokenCoroutine(this@SplashViewModel, viewModelScope)
            .let { isSuccess ->
                Timber.d(">> 토큰갱신 완료 결과 : $isSuccess")
                if (!isSuccess) {
                    MemberManager.signOut()
                }
            }
    }
}