package com.ddd.airplane.common.views.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.ddd.airplane.R
import com.google.android.material.tabs.TabLayout


/**
 *
 * @author jess
 */
class TabLayoutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TabLayout(context, attrs, defStyleAttr) {

    init {
        initLayout()
    }

    private fun initLayout() {

        this.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabReselected(tab: Tab?) {

            }

            override fun onTabSelected(tab: Tab?) {
                setTextAppearance(true, tab)
            }

            override fun onTabUnselected(tab: Tab?) {
                setTextAppearance(false, tab)
            }
        })
    }

    private fun setTextAppearance(isSelected: Boolean, tab: Tab?) {
        val views = arrayListOf<View>()
        tab?.run {
            view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
            views.forEach { view ->
                if (view is TextView) {
                    if (isSelected) {
                        TextViewCompat.setTextAppearance(view, R.style.tab_selected)
                    } else {
                        TextViewCompat.setTextAppearance(view, R.style.tab_unselected)
                    }
                }
            }
        }
    }

}