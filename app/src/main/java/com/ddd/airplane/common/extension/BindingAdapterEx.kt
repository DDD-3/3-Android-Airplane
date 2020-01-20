package com.ddd.airplane.common.extension

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.utils.tryCatch
import com.google.android.material.tabs.TabLayout

/**
 * 데이터 바인딩 어댑터
 */
object BindingAdapterEx {

    /**
     *  RecyclerView Adapter
     *
     * @param items
     * @param isClear
     */
    @JvmStatic
    @BindingAdapter(value = ["items", "isClear"], requireAll = false)
    fun RecyclerView.addAllItem(
        items: List<Any>?,
        isClear: Boolean = true
    ) {
        tryCatch {
            (this.adapter as? BaseRecyclerViewAdapter<Any, ViewDataBinding>)?.run {
                if (isClear) {
                    this.clear()
                }

                if (!items.isNullOrEmpty()) {
                    this.addAllItem(items)
                }
            }
        }
    }

    /**
     * ViewPager2 Adapter
     *
     * @param items
     * @param isClear
     */
    @JvmStatic
    @BindingAdapter(value = ["items", "isClear"], requireAll = false)
    fun ViewPager2.addAllItem(
        items: List<Any>?,
        isClear: Boolean = true
    ) {
        tryCatch {
            (this.adapter as? BaseRecyclerViewAdapter<Any, ViewDataBinding>)?.run {
                if (isClear) {
                    this.clear()
                }

                if (!items.isNullOrEmpty()) {
                    this.addAllItem(items)
                }
            }
        }
    }

    /**
     * ViewPager2 isUserInputEnabled
     */
    @JvmStatic
    @BindingAdapter("isUserInputEnabled")
    fun ViewPager2.setUserInputEnabled(
        isUserInputEnabled: Boolean = true
    ) {
        tryCatch {
            this.isUserInputEnabled = isUserInputEnabled
        }
    }

    @JvmStatic
    @BindingAdapter("items")
    fun TabLayout.addAllItem(
        items: List<String>?
    ) {
        tryCatch {
            // tab 추가
            items?.forEach {
                val tab = this.newTab().setText(it)
                this.addTab(tab)
            }
        }
    }
}