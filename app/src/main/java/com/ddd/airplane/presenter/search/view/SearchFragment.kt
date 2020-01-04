package com.ddd.airplane.presenter.search.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.SearchFragmentBinding
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.presenter.search.viewmodel.SearchViewModel

/**
 * 검색
 * @author jess
 */
class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>() {

    override val layoutRes = R.layout.search_fragment
    override val viewModelClass = SearchViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
