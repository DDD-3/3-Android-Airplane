package com.ddd.airplane.common.views.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.ddd.airplane.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import timber.log.Timber

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

//        <com.google.android.material.tabs.TabLayout
//        android:id="@+id/tl_type"
//        android:layout_width="match_parent"
//        android:layout_height="50dp"
//        android:background="@color/brand_black"
//        app:layout_constraintLeft_toLeftOf="parent"
//        app:layout_constraintRight_toRightOf="parent"
//        app:layout_constraintTop_toTopOf="parent"
//        app:tabIndicatorColor="#0cbfff"
//        app:tabIndicatorHeight="2dp"
//        app:tabMode="scrollable"
//        app:tabSelectedTextColor="@color/brand_white"
//        app:tabTextColor="#828181" />

        setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.brand_blue))

        this.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabReselected(tab: Tab?) {

            }

            override fun onTabSelected(tab: Tab?) {

            }

            override fun onTabUnselected(tab: Tab?) {

            }
        })
    }
}