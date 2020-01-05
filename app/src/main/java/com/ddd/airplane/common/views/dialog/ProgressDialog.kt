package com.ddd.airplane.common.views.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.ddd.airplane.R
import kotlinx.android.synthetic.main.alert_dialog.*

/**
 * Progress
 * ProgressDialog.Builder(this)
 *      .setCancelable(...)
 *      .setOnDismissListener {
 *
 *      }
 *      .show()
 * @param context
 */
class ProgressDialog(
    context: Context,
    private val builder: Builder
) : Dialog(context, R.style.DialogTheme) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.progress_dialog)
        initDialog()
        apply(builder)
    }

    private fun initDialog() {
        window?.attributes = WindowManager.LayoutParams().apply {
            copyFrom(window?.attributes)
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
        }
    }

    class Builder(val context: Context) {

        /**
         * 취소 가능여부
         */
        var cancelable: Boolean = true
            private set

        fun setCancelable(value: Boolean) = apply { cancelable = value }

        /**
         * Dialog dismiss listener
         */
        var dismissListener: (() -> Unit)? = null
            private set

        fun setOnDismissListener(listener: (() -> Unit)?) = apply {
            dismissListener = listener
        }

        /**
         * Dialog Show
         *
         */
        fun show() {
            ProgressDialog(context, this).show()
        }
    }

    /**
     * 적용
     * @param info
     */
    private fun apply(info: Builder) {
        setCancelable(info.cancelable)
        setOnDismissListener {
            info.dismissListener?.invoke()
        }

        ll_root.setOnClickListener {
            if (this@ProgressDialog.builder.cancelable) {
                dismiss()
            }
        }
    }

}