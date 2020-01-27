package com.ddd.airplane.presenter.home.view.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.extension.loadImage
import com.ddd.airplane.common.extension.showToast
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.databinding.HomeRankBinding
import com.ddd.airplane.databinding.HomeRankItemBinding
import timber.log.Timber

/**
 * 랭크
 *
 * @author jess
 * @since 2020.01.23
 */
class RankViewHolder(
    viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    private val binding = viewDataBinding as HomeRankBinding
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
        binding.title = itemData.title

        binding.rvRank.run {
            val listAdapter = object :
                BaseRecyclerViewAdapter<ChatRoomData, HomeRankItemBinding>(R.layout.home_rank_item) {
                override fun onBindData(
                    position: Int,
                    data: ChatRoomData,
                    dataBinding: HomeRankItemBinding
                ) {
                    dataBinding.run {
                        rank = position + 1
                        ivThumbnail.loadImage(data.subject?.thumbnail)
                    }
                }
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