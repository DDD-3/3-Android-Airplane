package com.ddd.airplane.common.utils

/**
 * 예외처리
 *
 * @param action
 */
inline fun  tryCatch(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
