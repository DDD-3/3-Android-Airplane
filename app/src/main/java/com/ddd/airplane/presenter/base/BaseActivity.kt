package com.ddd.airplane.presenter.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author jess
 */
abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity() {

    lateinit var dataBinding: D

    /**
     * 레이아웃 ID
     */
    @LayoutRes
    abstract fun setLayoutId(): Int

    /**
     * 데이터 바인딩 초기화
     */
    abstract fun initDataBinding()

    /**
     * 레이아웃 초기화
     */
    abstract fun initLayout()

    /**
     * onCreate 종료
     */
    abstract fun onCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, setLayoutId())
        initDataBinding()
        initLayout()
    }
}
