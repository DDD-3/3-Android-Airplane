package com.ddd.airplane.common.views.component

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import com.ddd.airplane.R
import kotlinx.android.synthetic.main.edit_text_view.view.*

/**
 *
 * @author jess
 */
class EditTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.edit_text_view, this, true)
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
                R.styleable.EditTextView,
                defStyleAttr,
                0
            )

//            <attr name="android:inputType" />
//            <attr name="android:hint" />
//            <attr name="android:maxLines" />
//            <attr name="android:imeOptions" />
//            <attr name="android:text" />

            et_input.inputType = typedValue.getInt(
                R.styleable.EditTextView_android_inputType,
                EditorInfo.TYPE_NULL
            )

            et_input.imeOptions = typedValue.getInt(
                R.styleable.EditTextView_android_imeOptions,
                EditorInfo.IME_ACTION_DONE
            )

            et_input.maxLines = typedValue.getInt(
                R.styleable.EditTextView_android_maxLines,
                1
            )
        }
    }

    /**
     * 최대 라인
     *
     * @param maxLine
     */
    private fun setMaxLine(maxLine: Int) {
        et_input.maxLines = maxLine
    }

    fun setText(text: String?) {
        text?.let {
            et_input.setText(text)
            tv_input.text = text
        }
    }

    fun getText() = et_input.text.toString()

    fun setOptionText(text: String?) {
        tv_option.text = text
    }

    fun setFocus(isFocus: Boolean) {

    }
}