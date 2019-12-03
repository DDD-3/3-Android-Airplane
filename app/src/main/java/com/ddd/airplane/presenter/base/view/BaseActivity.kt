package com.ddd.airplane.presenter.base.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddd.airplane.common.network.retrofit.RetrofitManager

/**
 * @author jess
 */
abstract class BaseActivity : AppCompatActivity() {

//    /**
//     * 레이아웃 처리 초기화
//     */
//    abstract fun initLayout()
//
//    /**
//     * 데이터 바인딩 초기화
//     */
//    abstract fun initDataBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initDataBinding()
//        initLayout()

        RetrofitManager.test
    }
}
