package com.ddd.airplane.presenter.home.view.viewholder

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.BR
import com.ddd.airplane.data.response.home.BannerData
import com.ddd.airplane.data.response.home.HomeData
import timber.log.Timber

class SwipeViewHolder(
    private val context: Context?,
    private val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun onBind(data: HomeData.ItemData<Any>?) {

        data?.let {
            val item = it.item as ArrayList<BannerData>
            Timber.d(item.toString())
//            viewDataBinding.setVariable(BR.item, item)
//            viewDataBinding.executePendingBindings()
        }
    }
}