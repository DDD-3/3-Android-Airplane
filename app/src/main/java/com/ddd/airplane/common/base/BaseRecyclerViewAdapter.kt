package com.ddd.airplane.common.base


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.BR

/**
 * Base RecyclerView Adapter for DataBinding
 *
 * @author jess
 * @since 2019-06-07
 */
@Suppress("UNCHECKED_CAST")
internal abstract class BaseRecyclerViewAdapter<T : Any, in D : ViewDataBinding>(
    @LayoutRes private val layoutId: Int = 0
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val list = mutableListOf<T>()
    private var itemClickListener: ((View, T) -> Unit)? = null
    var itemViewModel: BaseItemViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {

        require(layoutId > 0) { "Empty Layout Resource" }

        val dataBinding =
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )

        val viewHolder = createViewHolder(dataBinding, viewType)

        // OnClick
        dataBinding.root.setOnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION && itemClickListener != null) {
                itemClickListener?.invoke(it, list[viewHolder.adapterPosition])
            }
        }

        // Item ViewModel
        dataBinding.setVariable(BR.viewModel, itemViewModel)

        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(list[position])
        onBindData(position, list[position], holder.viewDataBinding as D)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    fun addAllItem(items: List<T>?) {
        items?.let {
            list.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun addItem(item: T) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): T {
        return list[position]
    }

    fun getItems(): List<T> {
        return list
    }

    /**
     * 아이템 클릭 리스너
     *
     * @param listener
     */
    open fun setOnItemClickListener(listener: ((View, T) -> Unit)?) = apply {
        this.itemClickListener = listener
    }

    open fun createViewHolder(dataBinding: ViewDataBinding, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(dataBinding)
    }

    open fun onBindData(position: Int, data: T, dataBinding: D) {

    }

}
