package com.ddd.airplane.presenter.search.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.SearchFragmentBinding
import com.ddd.airplane.presenter.base.BaseFragment

/**
 * 검색
 * @author jess
 */
class SearchFragment : BaseFragment<SearchFragmentBinding>() {

    override fun getLayoutId() = R.layout.search_fragment

    override fun initDataBinding() {
        super.initDataBinding()
        dataBinding.run {

        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
