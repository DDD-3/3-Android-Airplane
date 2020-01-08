package com.ddd.airplane.common.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.utils.tryCatch

/**
 * 데이터 바인딩 어댑터
 */
object BindingAdapterEx {

    @BindingAdapter(value = ["items", "isClear"], requireAll = false)
    fun RecyclerView.addAllItem(
        items: List<Any>?,
        isClear: Boolean = true
    ) {
        tryCatch {
            (this.adapter as? BaseRecyclerViewAdapter<Any, *>)?.run {
                if (isClear) {
                    this.clear()
                }

                if (!items.isNullOrEmpty()) {
                    this.addAllItem(items)
                }
            }
        }
    }

}
