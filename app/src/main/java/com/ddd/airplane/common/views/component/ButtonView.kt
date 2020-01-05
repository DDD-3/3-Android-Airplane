package com.ddd.airplane.common.views.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.ddd.airplane.R
import com.ddd.airplane.common.utils.DeviceUtils
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.edit_text_view.view.*

/**
 *
 * @author jess
 */
class ButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialButton(context, attrs, defStyleAttr) {

    init {
        intLayout(attrs, defStyleAttr)
    }

    @SuppressLint("CustomViewStyleable", "Recycle", "CheckResult")
    private fun intLayout(
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) {
        if (attrs != null) {


        }
    }
}
