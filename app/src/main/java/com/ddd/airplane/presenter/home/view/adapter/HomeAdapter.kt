package com.ddd.airplane.presenter.home.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.R
import com.ddd.airplane.common.consts.Home
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.home.BannerData
import com.ddd.airplane.data.response.home.HomeData.ItemData
import com.ddd.airplane.presenter.home.view.viewholder.SwipeViewHolder

/**
 * 홈 어뎁터
 */
class HomeAdapter(
    @NonNull private val context: Context?,
    @NonNull private val list: ArrayList<ItemData<Any>> = ArrayList()
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type.ordinal
    }

    private fun getType(viewType: Int) = Home.Type.values()[viewType]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val type = getType(viewType)
        val layoutId = when (type) {
            Home.Type.SWIPE_BANNER -> R.layout.home_swipe_banner
            else -> {
                R.layout.empty_view
            }
        }

        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return ViewHolder(dataBinding, type)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        tryCatch {

            val item = list[position]
            when (getType(getItemViewType(position))) {
                Home.Type.SWIPE_BANNER -> {
                    holder.swipe?.onBind(item)
                }

                else -> {

                }
            }
        }
    }

    fun addAllItem(items: ArrayList<ItemData<Any>>?) {
        items?.let {
            list.addAll(it)
            notifyDataSetChanged()
        }
    }

    /**
     * View Holder
     */
    inner class ViewHolder(
        viewDataBinding: ViewDataBinding,
        type: Home.Type
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {

        var swipe: SwipeViewHolder? = null

        init {
            when (type) {
                Home.Type.SWIPE_BANNER -> {
                    swipe = SwipeViewHolder(context, viewDataBinding)
                }

                else -> {

                }
            }
        }
    }
}
