package com.ddd.airplane.presenter.base


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Base RecyclerView Adapter for DataBinding
 *
 * @author jess
 * @since 2019-06-07
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseRecyclerViewAdapter<T : Any, in D : ViewDataBinding>(
    @LayoutRes private val layoutId: Int = -1
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder>() {

    private val list = mutableListOf<T>()

    abstract fun onBind(position: Int, model: T, dataBinding: D)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (layoutId < 0) {
            throw IllegalArgumentException("Empty Layout Resource")
        }

        val dataBinding =
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(position, list[position], holder.dataBinding as D)
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

    fun getItmes(): List<T> {
        return list
    }

    class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        var dataBinding: ViewDataBinding = binding
    }
}
