package com.ddd.airplane.presenter.home.view.viewholder

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.ddd.airplane.common.base.BaseViewHolder
import com.ddd.airplane.data.response.home.BannerData
import com.ddd.airplane.data.response.home.HomeData
import timber.log.Timber


class HorizontalViewHolder(
    private val context: Context?,
    binding: ViewDataBinding
) : BaseViewHolder<HomeData.ItemData<Any>>(binding) {

    override fun onBind(data: HomeData.ItemData<Any>?) {

        data?.let {
            val item = it.item as ArrayList<BannerData>
            Timber.d(item.toString())
//            viewDataBinding.setVariable(BR.item, item)
//            viewDataBinding.executePendingBindings()
        }
    }
}