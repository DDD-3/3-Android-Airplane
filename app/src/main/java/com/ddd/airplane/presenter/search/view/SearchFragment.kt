package com.ddd.airplane.presenter.search.view

import android.os.Bundle
import android.view.View
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.databinding.SearchFragmentBinding
import com.ddd.airplane.presenter.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.search_fragment.*

/**
 * 검색
 * @author jess
 */
class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>(),
    View.OnClickListener {

    override val layoutRes = R.layout.search_fragment
    override val viewModelClass = SearchViewModel::class.java

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {
        cl_delete.setOnClickListener(this)
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_delete -> {
                et_search.text = null
            }
        }
    }
}
