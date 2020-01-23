package com.ddd.airplane.presenter.home.view.viewholder

import android.content.Context
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.common.views.decoration.DividerItemSpace
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.data.response.home.RectangleData
import com.ddd.airplane.databinding.HomeHorizontalBinding
import com.ddd.airplane.databinding.HomeRectangleBinding
import com.ddd.airplane.databinding.ThumbnailGeneralItemBinding
import timber.log.Timber

/**
 * 가로 스크롤 배너
 *
 * @author jess
 * @since 2020.01.23
 */
class RectangleViewHolder(
    viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    private val binding = viewDataBinding as HomeRectangleBinding
    private var itemData = HomeData.ItemData<Any>()

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
                Timber.d(itemData.toString())
            }
        }
    }

    private fun initLayout() {
        val item = itemData.item as RectangleData?
        item?.thumbnail?.let {
            binding.ivThumbnail.loadImage(it)
        }
//        binding.item = itemData.item as RectangleData?
//        binding.executePendingBindings()
    }
}