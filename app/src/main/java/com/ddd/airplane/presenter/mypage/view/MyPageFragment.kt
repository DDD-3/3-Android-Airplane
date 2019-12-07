package com.ddd.airplane.presenter.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ddd.airplane.R
import com.ddd.airplane.databinding.MypageFragmentBinding
import com.ddd.airplane.presenter.base.view.BaseFragment

/**
 * 마이페이지
 * @author jess
 */
class MyPageFragment : BaseFragment<MypageFragmentBinding>() {

    override fun getLayoutId() = R.layout.mypage_fragment

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@MyPageFragment
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
