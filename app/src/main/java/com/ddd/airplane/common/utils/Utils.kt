package com.ddd.airplane.common.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun convertLongToYearDayTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }
}