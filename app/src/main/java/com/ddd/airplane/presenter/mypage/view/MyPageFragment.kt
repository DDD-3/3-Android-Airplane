package com.ddd.airplane.presenter.mypage.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BasePagedListAdapter
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.common.manager.ChatRoomManager
import com.ddd.airplane.common.views.decoration.DividerItemGrid
import com.ddd.airplane.data.response.chat.ProgramData
import com.ddd.airplane.databinding.MypageFragmentBinding
import com.ddd.airplane.presenter.mypage.viewmodel.MyPageViewModel
import kotlinx.android.synthetic.main.mypage_fragment.*

/**
 * 마이페이지
 * @author jess
 */
class MyPageFragment : BaseFragment<MypageFragmentBinding, MyPageViewModel>(),
    View.OnClickListener {

    override val layoutRes = R.layout.mypage_fragment
    override val viewModelClass = MyPageViewModel::class.java


    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initLayout() {
        setPagedList()
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    /**
     * Paged List 설정
     */
    private fun setPagedList() {
        /**
         * PagedList Adapter
         */
        val pagedAdapter = object : BasePagedListAdapter<ProgramData>(
            R.layout.thumbnail_grid_item,
            viewModel.diffCallback
        ) {

        }.apply {
            setOnItemClickListener { view, data ->
                ChatRoomManager.joinChatRoom(context, data)
            }
        }

        // adapter
        rv_subscribe.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemGrid(
                    2,
                    context.resources.getDimensionPixelSize(R.dimen.dp16)
                )
            )

            adapter = pagedAdapter
        }

        viewModel.pagedList.observe(viewLifecycleOwner, Observer {
            pagedAdapter.submitList(it)
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }
}
