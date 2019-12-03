package com.ddd.airplane.common.views.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ddd.airplane.R
import kotlinx.android.synthetic.main.header_view.view.*


/**
 * 공통 헤더뷰
 *
 * @author jess
 */
class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    enum class FinishType { BACK, CLOSE }

    init {
        LayoutInflater.from(context).inflate(R.layout.header_view, this, true)
        intLayout(attrs, defStyleAttr)
    }

    @SuppressLint("CustomViewStyleable", "Recycle", "CheckResult")
    private fun intLayout(
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) {
        if (attrs != null) {

            val typedValue = context.obtainStyledAttributes(
                attrs,
                R.styleable.HeaderView,
                defStyleAttr,
                0
            )

            // 종료형태
            setFinishType(typedValue)

            // 타이틀
            tv_title.text = typedValue.getString(R.styleable.HeaderView_title)

            // 옵션 텍스트
            tv_text_option.text = typedValue.getString(R.styleable.HeaderView_textOption)

            // 왼쪽 옵션 이미지
            iv_left_option.setImageDrawable(typedValue.getDrawable(R.styleable.HeaderView_leftOption))

            // 왼쪽 옵션 이미지
            iv_right_option.setImageDrawable(typedValue.getDrawable(R.styleable.HeaderView_rightOption))
        }
    }

    /**
     * 종료형태
     */
    private fun setFinishType(typedValue: TypedArray) {
        val finishType = FinishType.values()[typedValue.getInt(
            R.styleable.HeaderView_finishType,
            FinishType.BACK.ordinal
        )]

        val icon = when (finishType) {
            FinishType.CLOSE -> {
                R.drawable.ic_sample
            }
            else -> {
                R.drawable.ic_sample
            }
        }

        iv_finish.setImageResource(icon)

    }
}