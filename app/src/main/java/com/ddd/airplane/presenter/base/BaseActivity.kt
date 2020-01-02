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
    abstract fun getLayoutId(): Int


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
        initDataBinding()
        initLayout()
        onCreated(savedInstanceState)
    }

    /**
     * 데이터 바인딩 초기화
     */
    protected open fun initDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        dataBinding.run {
            lifecycleOwner = this@BaseActivity
        }
    }
}
