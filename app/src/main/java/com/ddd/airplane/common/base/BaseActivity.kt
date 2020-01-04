package com.ddd.airplane.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.ddd.airplane.BR

/**
 * @author jess
 */
abstract class BaseActivity<VD : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    /**
     * ViewDataBinding
     */
    lateinit var binding: VD

    /**
     * ViewModel Class
     */
    protected abstract val viewModelClass: Class<VM>

    /**
     * 레이아웃 ID
     */
    protected abstract val layoutRes: Int


    /**
     * 레이아웃 초기화
     */
    abstract fun initLayout()

    /**
     * onCreate 종료
     */
    abstract fun onCreated(savedInstanceState: Bundle?)

    /**
     * AAC ViewModel
     */
    protected val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        createViewModel(viewModelClass)
    }

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
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.run {
            lifecycleOwner = this@BaseActivity
            setVariable(BR._all, viewModel)
        }
    }

    /**
     * Create AAC ViewModel
     */
    private fun <VM : ViewModel> createViewModel(viewModelClass: Class<VM>): VM {
        return ViewModelProviders.of(this).get(viewModelClass)
    }
}
