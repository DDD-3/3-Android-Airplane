package com.ddd.airplane.presenter.mypage.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseFragment
import com.ddd.airplane.common.base.BasePagedListAdapter
import com.ddd.airplane.common.views.decoration.DividerItemGrid
import com.ddd.airplane.data.response.chat.ChatRoomData
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
        viewModel.run {
            pagedList.observe(this@MyPageFragment, Observer {
                pagedAdapter.submitList(it)
            })
        }
    }

    override fun initLayout() {
        val views = arrayOf(cl_profile)
        views.forEach {
            it.setOnClickListener(this)
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

        pagedAdapter.run {
            setOnItemClickListener { view, chatRoomData ->

            }
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_profile -> { // 로그인화면 (임시)

            }
        }
    }

    /**
     * PagedList Adapter
     */
    private val pagedAdapter = object : BasePagedListAdapter<ChatRoomData>(
        R.layout.thumbnail_grid_item,
        viewModel.diffCallback
    ) {

    }
}
