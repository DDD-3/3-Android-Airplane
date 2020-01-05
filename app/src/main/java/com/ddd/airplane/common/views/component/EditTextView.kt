package com.ddd.airplane.common.views.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.ddd.airplane.R
import com.ddd.airplane.common.utils.DeviceUtils
import com.ddd.airplane.databinding.EditTextViewBinding
import kotlinx.android.synthetic.main.edit_text_view.view.*

/**
 *
 * @author jess
 */
class EditTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {

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

    private var binding = EditTextViewBinding.inflate(LayoutInflater.from(context), this, true)
    private var valid = Valid.NORMAL
    private var isPasswordShow = false // 비밀번호 노출 여부
    private var isValid = false // 유효성 여부

    // 리스너
    private var doneListener: (() -> Unit)? = null
    private var changedListener: (() -> Unit)? = null
    private var optionClickListener: (() -> Unit)? = null
    private var focusListener: ((Boolean) -> Unit)? = null
    private var validListener: ((View, Boolean) -> Unit)? = null

    init {
        initLayout(attrs, defStyleAttr)
        initListener()
    }

    @SuppressLint("CustomViewStyleable", "Recycle", "CheckResult")
    private fun initLayout(
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

            setInputType(
                typedValue.getInt(
                    R.styleable.EditTextView_android_inputType,
                    InputType.TYPE_CLASS_TEXT
                )
            )

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
     * 리스너
     */
    private fun initListener() {
        et_input.run {

            addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun afterTextChanged(editable: Editable) {}

                override fun onTextChanged(
                    charSequence: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    when (valid) {
                        Valid.EMAIL -> checkValidEmail(charSequence)
                        Valid.PASSWORD -> checkValidPassword(charSequence)
                        else -> {

                        }
                    }
                    changedListener?.invoke()
                }
            })

            onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
                binding.isFocus = hasFocus
                focusListener?.invoke(hasFocus)
            }

            setOnEditorActionListener { view, i, keyEvent ->
                when (i) {
                    EditorInfo.IME_ACTION_DONE -> {
                        doneListener?.invoke()
                    }
                }
                false
            }
        }

        // 옵션 클릭리스너
        tv_option.setOnClickListener(this)
    }

    /**
     * Text Changed
     */
    fun setOnTextChangedListener(listener: (() -> Unit)?) {
        changedListener = listener
    }

    /**
     * Done Action
     */
    fun setOnDoneListener(listener: (() -> Unit)?) {
        this.doneListener = listener
    }

    /**
     * Done Action
     */
    fun setOnFocusListener(listener: ((Boolean) -> Unit)?) {
        this.focusListener = listener
    }

    /**
     * 검증 리스너
     */
    fun setOnValidListener(listener: ((View, Boolean) -> Unit)?) {
        this.validListener = listener
    }

    /**
     * 옵션 클릭 리스너
     */
    fun setOnOptionClickListener(listener: (() -> Unit)?) {
        this.optionClickListener = listener
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

        et_input.imeOptions = typedValue.getInt(
            R.styleable.EditTextView_android_imeOptions,
            EditorInfo.IME_ACTION_DONE
        )
    }

    /**
     * Input Type
     */
    private fun setInputType(inputType: Int) {
        et_input.inputType = inputType
    }

    /**
     * 힌트
     */
    private fun setHint(@StringRes stringRes: Int) = apply {
        et_input.setText(stringRes)
    }

    /**
     * 라벨
     */
    private fun setLabel(@StringRes stringRes: Int) = apply {
        tv_label.setText(stringRes)
    }

    /**
     * 텍스트
     */
    fun setText(@StringRes stringRes: Int) = apply {
        stringRes.let {
            et_input.setText(it)
            tv_input.setText(it)
        }
    }

    /**
     * 텍스트
     */
    fun setText(text: String?) = apply {
        if (!text.isNullOrEmpty()) {
            text.let {
                et_input.setText(it)
                tv_input.text = it
            }
        }
    }

    fun getText() = et_input.text.toString()

    /**
     * 오른쪽 옵션 텍스트
     */
    fun setOptionText(@StringRes stringRes: Int) = apply {
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
    fun setFocus(isFocus: Boolean) = apply {
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
    fun setDescription(@StringRes stringRes: Int) = apply {
        tv_description.setText(stringRes)
    }

    /**
     * 이메일 검증
     */
    fun checkValidEmail(char: CharSequence) {
        isValid = char.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(char).matches()
        cl_info.visibility = if (isValid) View.GONE else View.VISIBLE
        validListener?.invoke(cl_info, isValid)
    }

    /**
     * 비밀번호 검증
     */
    fun checkValidPassword(char: CharSequence) {
        isValid = char.length >= 4
        cl_info.visibility = if (isValid) View.GONE else View.VISIBLE
        validListener?.invoke(cl_info, isValid)
    }

    /**
     * 비밀번호 숨김/보임
     */
    private fun showPassword() {
        if (et_input.text?.isNotEmpty() == true) {
            tv_option.setText(
                if (isPasswordShow) {
                    // 비밀번호 숨김 설정
                    setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    R.string.sign_up_hide
                } else {
                    // 비밀번호 숨김 설정
                    setInputType(InputType.TYPE_CLASS_TEXT)
                    R.string.sign_up_show
                }
            )
            et_input.setSelection(et_input.length())
            isPasswordShow = !isPasswordShow
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option -> {
                when (valid) {
                    Valid.PASSWORD -> showPassword()
                }
                optionClickListener?.invoke()
            }
        }
    }
}
