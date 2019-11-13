package com.ddd.airplane.common.extension

import android.content.Context
import android.widget.Toast

/**
 * Toast
 *
 * @param resId
 */
fun Context.showToast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}

/**
 * Toast
 *
 * @param text
 */
fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
