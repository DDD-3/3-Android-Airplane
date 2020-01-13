package com.ddd.airplane.common.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.common.views.component.ViewPagerInterceptView
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

    /**
     * ViewPagerInterceptView Adapter
     *
     * @param items
     * @param isClear
     */
    @JvmStatic
    @BindingAdapter(value = ["items", "isClear"], requireAll = false)
    fun ViewPagerInterceptView.addAllItem(
        items: List<Any>?,
        isClear: Boolean = true
    ) {
        tryCatch {
            (this.viewPager2.adapter as? BaseRecyclerViewAdapter<Any, *>)?.run {
                if (isClear) {
                    this.clear()
                }

                if (!items.isNullOrEmpty()) {
                    this.addAllItem(items)
                }
            }
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