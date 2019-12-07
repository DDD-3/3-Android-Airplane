package com.ddd.airplane.presenter.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddd.airplane.R
import com.ddd.airplane.databinding.SearchFragmentBinding
import com.ddd.airplane.presenter.base.view.BaseFragment

/**
 * 검색
 * @author jess
 */
class SearchFragment : BaseFragment<SearchFragmentBinding>() {

    override fun getLayoutId() = R.layout.search_fragment

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@SearchFragment
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
