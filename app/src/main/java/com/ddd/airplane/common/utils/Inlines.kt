package com.ddd.airplane.common.utils

inline fun  tryCatch(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
