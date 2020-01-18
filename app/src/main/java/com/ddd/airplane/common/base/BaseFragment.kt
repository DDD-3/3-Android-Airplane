package com.ddd.airplane.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.ddd.airplane.BR
import com.ddd.airplane.R
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.common.utils.DeviceUtils
import com.ddd.airplane.common.views.dialog.ProgressDialog
import com.jaeger.library.StatusBarUtil

abstract class BaseFragment<VD : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    /**
     * ViewDataBinding
     */
    lateinit var binding: VD

    /**
     * 레이아웃 ID
     */
    protected abstract val layoutRes: Int

    /**
     * ViewModel Class
     */
    protected abstract val viewModelClass: Class<VM>

    /**
     * 레이아웃 초기화
     */
    abstract fun initLayout()

    /**
     * onCreate 종료
     */
    abstract fun onCreated(savedInstanceState: Bundle?)

    /**
     * Create AAC ViewModel
     */
    private fun <VM : ViewModel> createViewModel(viewModelClass: Class<VM>): VM {
        return ViewModelProviders.of(this).get(viewModelClass)
    }

    /**
     * AAC ViewModel
     */
    protected val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        createViewModel(viewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            layoutRes,
            container,
            false
        )

        binding.run {
            lifecycleOwner = this@BaseFragment
            setVariable(BR.viewModel, viewModel)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initStatusBarColor()
        initDataBinding()
        initLayout()
        onCreated(savedInstanceState)
    }

    private fun initStatusBarColor() {
        DeviceUtils.setLightStatusBar(activity, false)
        DeviceUtils.setStatusBarColor(activity, R.color.brand_black)
    }

    /**
     * 데이터 바인딩 초기화
     */
    protected open fun initDataBinding() {

    }

    /**
     * 네트워크 UI 처리
     */
    private fun initNetworkStatus() {

        var progressDialog: ProgressDialog? = null
        context?.let {
            progressDialog = ProgressDialog(it).apply {
                setCancelable(false)
            }
        }

        viewModel.run {
            isProgress.observe(this@BaseFragment, Observer {
                activity?.runOnUiThread {
                    progressDialog?.run {
                        if (it) show() else dismiss()
                    }
                }
            })

            toast.observe(this@BaseFragment, Observer {
                activity?.runOnUiThread {
                    context.showToast(it)
                }
            })
        }
    }
}
