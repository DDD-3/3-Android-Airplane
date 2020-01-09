package com.ddd.airplane.common.views.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.ddd.airplane.R
import com.google.android.material.button.MaterialButton
import timber.log.Timber

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
        initLayout()
    }

    private fun initLayout() {

        // 버튼 높이
        minHeight = context.resources.getDimensionPixelSize(R.dimen.dp48)

        // 텍스트컬러
        this.setTextColor(ContextCompat.getColor(context, R.color.color_white))

        // 폰트
        val typeface = ResourcesCompat.getFont(context, R.font.notosans_bold)
        this.typeface = typeface

        // text size
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.dp16))

//        includeFontPadding = false

        // 라운드
        cornerRadius = context.resources.getDimensionPixelSize(R.dimen.dp4)

        // 대문자 비활성화
        this.isAllCaps = false

        // 문자 간격
        this.letterSpacing = 0.toFloat()

        // 정렬
        this.gravity = Gravity.CENTER

        // 버튼 기본적으로 활성화
        isEnabled = true

    }

    fun setText(text: String) {
        Timber.d(text)
        this.text = text
    }

    /**
     * 활성 / 비활성
     * @param isEnable
     */
    override fun setEnabled(isEnable: Boolean) {
        super.setEnabled(isEnable)
        this.run {
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