package com.ddd.airplane.common.utils

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.roundToInt


object DeviceUtils {

    /**
     * 키보드 올림
     *
     * @param view
     */
    fun showKeyboard(view: View?) {
        view?.let {
            val inputManager =
                it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    /**
     * 키보드 내림
     *
     * @param view
     */
    fun hideKeyboard(view: View?) {
        view?.let {
            val inputManager =
                it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
    }

    fun dpToPx(context: Context, dp: Int): Int {
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        )
        return px.roundToInt()

    }
}