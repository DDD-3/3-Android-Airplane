package com.ddd.airplane.presenter.home.view.viewholder

import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.common.views.decoration.DividerItemSpace
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.databinding.HomeHorizontalBinding
import com.ddd.airplane.databinding.ThumbnailGeneralItemBinding
import kotlinx.android.synthetic.main.home_horizontal.view.*
import timber.log.Timber

/**
 * 가로 스크롤 배너
 *
 * @author jess
 * @since 2020.01.23
 */
class HorizontalViewHolder(
    viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    private val view = viewDataBinding.root
    private val binding = viewDataBinding as HomeHorizontalBinding
    private var itemData = HomeData.ItemData()
    private val bannerList = ArrayList<ChatRoomData>()

    fun onBind(item: HomeData.ItemData?) {
        tryCatch {
            initData(item)
            initLayout()
        }
    }

    private fun initData(item: HomeData.ItemData?) {
        tryCatch {
            item?.let {
                itemData = it
                bannerList.addAll(it.item as ArrayList<ChatRoomData>)
                Timber.d(bannerList.toString())
            }
        }
    }

    private fun initLayout() {

        // 타이틀
        binding.title = itemData.title

        view.rv_horizontal.run {
            addItemDecoration(
                DividerItemSpace(
                    LinearLayout.HORIZONTAL,
                    context.resources.getDimensionPixelSize(R.dimen.dp12)
                )
            )

            val listAdapter = object :
                BaseRecyclerViewAdapter<ChatRoomData, ThumbnailGeneralItemBinding>(R.layout.thumbnail_general_item) {
            }.apply {
                setOnItemClickListener { view, data ->
                    context.showToast(data?.roomId.toString())
                }
            }

            adapter = listAdapter
            listAdapter.addAllItem(bannerList)
        }
    }
}