package com.ddd.airplane.common.extension

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.utils.DeviceUtils
import com.ddd.airplane.common.utils.tryCatch
import com.google.android.material.tabs.TabLayout


/**
 *  RecyclerView Adapter
 *
 * @param items
 * @param isClear
 */

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
@BindingAdapter("isUserInputEnabled")
fun ViewPager2.setUserInputEnabled(
    isUserInputEnabled: Boolean = true
) {
    tryCatch {
        this.isUserInputEnabled = isUserInputEnabled
    }
}
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

/**
 * R.drawable 로드
 *
 * @param drawableRes
 */
@BindingAdapter("imageResource")
fun ImageView.setImageResource(@DrawableRes drawableRes: Int) {
    this.setImageResource(drawableRes)
}

/**
 * 스크린 사이즈 width 설정
 */
@BindingAdapter("ratioWidth")
fun View.setRatioWidth(ratio: Int) {
    try {
        this.layoutParams.width = DeviceUtils.getScreenWidth(this.context, ratio)
    } catch (e: Exception) {
        e.printStackTrace()
    }
        
}