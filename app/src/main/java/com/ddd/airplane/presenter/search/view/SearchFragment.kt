package com.ddd.airplane.presenter.search.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BasePagedListAdapter
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.common.manager.ChatRoomManager
import com.ddd.airplane.common.utils.DeviceUtils
import com.ddd.airplane.common.views.decoration.DividerItemSpace
import com.ddd.airplane.data.response.chat.ProgramData
import com.ddd.airplane.databinding.SearchFragmentBinding
import com.ddd.airplane.presenter.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.search_fragment.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.view.KeyEvent


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
        viewModel.run {
            isSearchAdapter.observe(viewLifecycleOwner, Observer {
                setSearchPagedList()
            })
        }
    }

    override fun initLayout() {
        cl_delete.setOnClickListener(this)

        // adapter
        rv_many.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemSpace(
                    LinearLayout.HORIZONTAL,
                    context.resources.getDimensionPixelSize(R.dimen.dp12)
                )
            )
        }

        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.initSearch()
                    return true
                }
                return false
            }
        })
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        et_search.requestFocus()
        DeviceUtils.showKeyboard(et_search)
    }

    override fun onPause() {
        et_search.clearFocus()
        DeviceUtils.hideKeyboard(et_search)
        super.onPause()
    }

    /**
     * 검색 RecyclerView Paged List 설정
     */
    private fun setSearchPagedList() {

        /**
         * PagedList Adapter
         */
        val pagedAdapter = object : BasePagedListAdapter<ProgramData>(
            R.layout.search_item,
            viewModel.diffCallback
        ) {

        }.apply {
            setOnItemClickListener { view, data ->
                ChatRoomManager.joinChatRoom(context, data)
            }
        }

        // adapter
        rv_search.adapter = pagedAdapter

        viewModel.searchPagedList.observe(viewLifecycleOwner, Observer {
            pagedAdapter.submitList(it)
        })
    }

    /**
     * 많이 참여한 방송 RecyclerView Paged List 설정
     */
    private fun setManyPagedList() {

        /**
         * PagedList Adapter
         */
        val pagedAdapter = object : BasePagedListAdapter<ProgramData>(
            R.layout.thumbnail_general_item,
            viewModel.diffCallback
        ) {

        }.apply {
            setOnItemClickListener { view, data ->
                context?.showToast(data?.roomId.toString())
            }
        }

        // adapter
        rv_search.adapter = pagedAdapter

        viewModel.searchPagedList.observe(viewLifecycleOwner, Observer {
            pagedAdapter.submitList(it)
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_delete -> {
                et_search.text = null
            }
        }
    }
}
