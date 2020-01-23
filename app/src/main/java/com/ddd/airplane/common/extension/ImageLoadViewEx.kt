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
 * Image Load View Extension
 */
object ImageLoadViewEx {


    /**
     * 스크린 사이즈 width 설정
     */
    @JvmStatic
    @BindingAdapter("ratioWidth")
    fun View.setRatioWidth(ratio: Int) {
        try {
            this.layoutParams.width = DeviceUtils.getScreenWidth(this.context, ratio)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}