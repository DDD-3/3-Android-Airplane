package com.ddd.airplane.common.views.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.ddd.airplane.R
import com.ddd.airplane.databinding.ButtonViewBinding
import kotlinx.android.synthetic.main.button_view.view.*
import timber.log.Timber

/**
 *
 * @author jess
 */
class ButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding = ButtonViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        initLayout()
    }

    private fun initLayout() {
        bt_view.setTextColor(ContextCompat.getColor(context, R.color.color_white))
        isEnabled = true
    }

    fun setText(text: String) {
        Timber.d(text)
        bt_view.text = text
    }

    /**
     * 활성 / 비활성
     * @param isEnable
     */
    override fun setEnabled(isEnable: Boolean) {
        super.setEnabled(isEnable)
        bt_view.run {
            if (isEnable) {
                backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.color_button_primary)
                strokeWidth = 0
            } else {
                backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.color_black)
                setStrokeWidthResource(R.dimen.dp2)
                setStrokeColorResource(R.color.color_white)
            }

            isClickable = isEnable
        }

    }
}
