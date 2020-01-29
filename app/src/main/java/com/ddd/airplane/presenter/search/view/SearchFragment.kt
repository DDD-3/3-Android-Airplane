package com.ddd.airplane.presenter.search.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.databinding.SearchFragmentBinding
import com.ddd.airplane.presenter.search.viewmodel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        val watcher = object : TextWatcher {

            private var searchFor: String? = null

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val searchText = s.toString().trim()
                if (searchText == searchFor) {
                    return
                }
                searchFor = searchText

                CoroutineScope(Dispatchers.Main).launch {
                    delay(300)  // debounce timeOut
                    if (searchText != searchFor) {
                        return@launch
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit
        }
    }
}
