package com.ddd.airplane.common.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.BR

internal abstract class BasePagedListAdapter<T : Any>(
    @LayoutRes private val layoutId: Int = 0,
    diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, BaseViewHolder<T>>(diffCallback) {

    private var itemClickListener: ((View, T) -> Unit)? = null
    var itemViewModel: BaseItemViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {

        require(layoutId > 0) { "Empty Layout Resource" }

        val dataBinding = createViewDataBinding(parent)
        val viewHolder = createViewHolder(dataBinding, viewType)

        // OnClick
        dataBinding.run {

            // click
            root.setOnClickListener { view ->
                val item = getItem(viewHolder.adapterPosition)
                item?.let { t ->
                    if (viewHolder.adapterPosition != RecyclerView.NO_POSITION && itemClickListener != null) {
                        itemClickListener?.invoke(view, t)
                    }
                }
            }

            // Item ViewModel
            dataBinding.setVariable(BR.viewModel, itemViewModel)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(getItem(position))
    }

    /**
     * 아이템 클릭 리스너
     *
     * @param listener
     */
    open fun setOnItemClickListener(listener: ((View, T) -> Unit)?) = apply {
        this.itemClickListener = listener
    }

    open fun createViewDataBinding(parent: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
    }

    open fun createViewHolder(dataBinding: ViewDataBinding, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(dataBinding)
    }

}
