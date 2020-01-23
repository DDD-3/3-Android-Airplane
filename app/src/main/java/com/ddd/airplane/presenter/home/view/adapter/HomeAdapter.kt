package com.ddd.airplane.presenter.home.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.R
import com.ddd.airplane.common.consts.Home
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.presenter.home.view.viewholder.HorizontalViewHolder
import com.ddd.airplane.presenter.home.view.viewholder.MainSwipeViewHolder
import com.ddd.airplane.presenter.home.view.viewholder.RectangleViewHolder

class HomeAdapter constructor(
    private val context: Context?
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    val list = arrayListOf<HomeData.ItemData<Any>>()

    private fun getStyle(viewType: Int) = Home.Style.values()[viewType]

    fun addAllItem(items: ArrayList<HomeData.ItemData<Any>>?) = apply {
        items?.let {
            list.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun createViewDataBinding(parent: ViewGroup, layoutId: Int): ViewDataBinding {
        return DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].style.ordinal
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val style = getStyle(viewType)
        val layoutId = when (style) {
            Home.Style.MAIN_SWIPE_BANNER -> R.layout.home_swipe_banner
            Home.Style.HORIZONTAL -> R.layout.home_horizontal
            Home.Style.RECTANGLE_BANNER -> R.layout.home_rectangle
            else -> {
                R.layout.empty_view
            }
        }
        return ViewHolder(createViewDataBinding(parent, layoutId), style)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        tryCatch {
            val item = list[position]
            when (getStyle(getItemViewType(position))) {
                Home.Style.MAIN_SWIPE_BANNER -> holder.swipe?.onBind(item)
                Home.Style.HORIZONTAL -> holder.horizontal?.onBind(item)
                Home.Style.RECTANGLE_BANNER -> holder.rectangle?.onBind(item)
                else -> {

                }
            }
        }
    }

    /**
     * View Holder
     */
    inner class ViewHolder(
        viewDataBinding: ViewDataBinding,
        style: Home.Style = Home.Style.EMPTY
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {

        var swipe: MainSwipeViewHolder? = null
        var horizontal: HorizontalViewHolder? = null
        var rectangle: RectangleViewHolder? = null

        init {
            when (style) {
                Home.Style.MAIN_SWIPE_BANNER -> {
                    swipe = MainSwipeViewHolder(viewDataBinding)
                }
                Home.Style.HORIZONTAL -> {
                    horizontal = HorizontalViewHolder(viewDataBinding)
                }
                Home.Style.RECTANGLE_BANNER -> {
                    rectangle = RectangleViewHolder(viewDataBinding)
                }
                else -> {

                }
            }
        }
    }

}