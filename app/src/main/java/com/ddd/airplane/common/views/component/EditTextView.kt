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

    enum class Mode {
        // 일반
        NORMAL,
        // EditText 를 활성화 하지 않음, TextView 를 활성화함
        DISABLE
    }

    /**
     * 유효성 체크
     */
    enum class Valid {
        NORMAL,
        EMAIL,
        PASSWORD
    }

    private var valid = Valid.NORMAL

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

            setMode(typedValue)

            setEditText(typedValue)

            setText(
                typedValue.getResourceId(
                    R.styleable.EditTextView_android_text,
                    R.string.empty
                )
            )

            setLabel(
                typedValue.getResourceId(
                    R.styleable.EditTextView_label,
                    R.string.empty
                )
            )

            setDescription(
                typedValue.getResourceId(
                    R.styleable.EditTextView_description,
                    R.string.empty
                )
            )

            setOptionText(
                typedValue.getResourceId(
                    R.styleable.EditTextView_optionText,
                    R.string.empty
                )
            )

            setFocus(
                typedValue.getBoolean(
                    R.styleable.EditTextView_focus,
                    false
                )
            )

            valid = Valid.values()[typedValue.getInt(
                R.styleable.EditTextView_valid,
                Valid.NORMAL.ordinal
            )]

            typedValue.recycle()
        }
    }

    /**
     * Input 형태
     */
    private fun setMode(typedValue: TypedArray) {
        val mode =
            Mode.values()[typedValue.getInt(
                R.styleable.EditTextView_mode,
                Mode.NORMAL.ordinal
            )]

        when (mode) {
            Mode.NORMAL -> {
                et_input.visibility = View.VISIBLE
                tv_input.visibility = View.GONE
            }

            Mode.DISABLE -> {
                et_input.visibility = View.GONE
                tv_input.visibility = View.VISIBLE
            }
        }
    }

    /**
     * EditText
     *
     * @param typedValue
     */
    private fun setEditText(typedValue: TypedArray) {

        setHint(
            typedValue.getResourceId(
                R.styleable.EditTextView_android_hint,
                R.string.empty
            )
        )

        et_input.maxLines = typedValue.getInt(
            R.styleable.EditTextView_android_maxLines,
            1
        )

        et_input.inputType = typedValue.getInt(
            R.styleable.EditTextView_android_inputType,
            EditorInfo.TYPE_CLASS_TEXT
        )

        et_input.imeOptions = typedValue.getInt(
            R.styleable.EditTextView_android_imeOptions,
            EditorInfo.IME_ACTION_DONE
        )
    }

    /**
     * 힌트
     */
    private fun setHint(@StringRes stringRes: Int) {
        et_input.setText(stringRes)
    }

    /**
     * 라벨
     */
    private fun setLabel(@StringRes stringRes: Int) {
        tv_label.setText(stringRes)
    }

    /**
     * 텍스트
     */
    fun setText(@StringRes stringRes: Int) {
        et_input.setText(stringRes)
        tv_input.setText(stringRes)
    }

    /**
     * 텍스트
     */
    @SuppressLint("ResourceType")
    fun setText(text: String?) {
        if (text.isNullOrEmpty()) {
            return
        }
        text.let {
            et_input.setText(it)
            tv_input.text = it
        }
    }

    fun getText() = et_input.text.toString()

    /**
     * 오른쪽 옵션 텍스트
     */
    fun setOptionText(@StringRes stringRes: Int) {
        tv_option.setText(stringRes)
        tv_option.visibility = if (tv_option.text.toString().isNotEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    /**
     * focus 여부
     *
     * @param isFocus true : 포커싱, 키보드 올라옴
     */
    fun setFocus(isFocus: Boolean) {
        if (isFocus) {
            et_input.run {
                isFocusableInTouchMode = true
                isFocusable = true
                requestFocus()
            }
            DeviceUtils.showKeyboard(et_input)
        } else {
            tv_label.run {
                isFocusableInTouchMode = true
                isFocusable = true
                requestFocus()
            }
        }
    }

    /**
     * 하단문구
     */
    fun setDescription(@StringRes stringRes: Int) {
        tv_description.setText(stringRes)
    }

}
