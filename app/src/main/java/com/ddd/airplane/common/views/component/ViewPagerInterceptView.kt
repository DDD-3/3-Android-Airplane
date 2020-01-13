package com.ddd.airplane.common.views.component

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2


/**
 * @author jess
 */
class ViewPagerInterceptView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var viewPager2: ViewPager2 = ViewPager2(context)
    var childViewPager: ViewPager2? = null
    var isScrollEnable = false
        private set

    init {
        initLayout()
    }

    private fun initLayout() {
        this.removeAllViews()
        this.addView(viewPager2)

        viewPager2.isUserInputEnabled = false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        childViewPager?.requestDisallowInterceptTouchEvent(false)
        return super.onInterceptTouchEvent(ev)
    }

    fun setChildView(viewPager: ViewPager2?) {
        this.childViewPager = viewPager
    }

}