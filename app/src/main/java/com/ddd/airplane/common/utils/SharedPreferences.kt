package com.ddd.airplane.common.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * SharedPreferences
 */
object SharedPreferences {

    private lateinit var prefs: SharedPreferences

    val FILE_NAME = "prefs"
    val SAMPLE_TEXT_KEY = "sampleText"

    fun init(context: Context?) {
        context?.let {
            prefs = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        }
    }

    var sampleText: String?
        get() = prefs.getString(SAMPLE_TEXT_KEY, "")
        set(value) = prefs.edit().putString(SAMPLE_TEXT_KEY, value).apply()

}