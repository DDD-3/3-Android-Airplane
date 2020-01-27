package com.ddd.airplane.presenter.home.view.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.common.views.decoration.DividerItemGrid
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.databinding.HomeGridBinding
import com.ddd.airplane.databinding.ThumbnailGridItemBinding
import timber.log.Timber

/**
 * 그리드 배너
 *
 * @author jess
 * @since 2020.01.23
 */
class GridViewHolder(
    viewDataBinding: ViewDataBinding,
    private val span: Int = 2
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    private val binding = viewDataBinding as HomeGridBinding
    private var itemData = HomeData.ItemData<Any>()
    private val bannerList = ArrayList<ChatRoomData>()

    fun onBind(item: HomeData.ItemData<Any>?) {
        tryCatch {
            initData(item)
            initLayout()
        }
    }

    private fun initData(item: HomeData.ItemData<Any>?) {
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
        binding.run {
            title = itemData.title
            spanCount = span

            rvGrid.run {
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemGrid(
                        2,
                        context.resources.getDimensionPixelSize(R.dimen.dp16)
                    )
                )

                val listAdapter = object :
                    BaseRecyclerViewAdapter<ChatRoomData, ThumbnailGridItemBinding>(R.layout.thumbnail_grid_item) {
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
}