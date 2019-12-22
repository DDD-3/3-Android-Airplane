package com.ddd.airplane.common

import android.content.Context
import android.content.SharedPreferences

class SBSSharedPreferences(context: Context) {

    val FILE_NAME = "prefs"
    val SAMPLE_TEXT_KEY = "sampleText"
    private val prefs: SharedPreferences = context.getSharedPreferences(FILE_NAME, 0)

    var sampleText: String?
        get() = prefs.getString(SAMPLE_TEXT_KEY, "")
        set(value) = prefs.edit().putString(SAMPLE_TEXT_KEY, value).apply()
}