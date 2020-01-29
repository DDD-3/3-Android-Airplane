package com.ddd.airplane.presenter.home.view.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.R
import com.ddd.airplane.common.extension.loadImage
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.home.BannerData
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.databinding.HomeRectangleBinding
import kotlinx.android.synthetic.main.home_rectangle.view.*
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

    private val context = viewDataBinding.root.context
    private val view = viewDataBinding.root
    private val binding = viewDataBinding as HomeRectangleBinding
    private var itemData = HomeData.ItemData()

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
                Timber.d(itemData.toString())
            }
        }
    }

    private fun initLayout() {
        view.iv_thumbnail.loadImage(
            (itemData.item as BannerData?)?.thumbnailUrl,
            corners = context.resources.getDimensionPixelSize(R.dimen.dp4)
        )

        view.cl_container.setOnClickListener {

        }
    }
}