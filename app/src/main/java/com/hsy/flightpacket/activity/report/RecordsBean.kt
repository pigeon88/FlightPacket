package com.hsy.flightpacket.activity.report

import java.text.SimpleDateFormat
import java.util.*

data class RecordsBean(
        val item1: String?,
        val item2: Date?,
        val item3: Date?,
        val item4: Int?,
        val item5: Int?,
        val item6: Int?,
        val item7: Int?,
        val item8: Int?,
        val item9: Int?,
        val item10: String?
) {
    fun getItem2Str(): String? {
        return getDateFormat(item2)
    }

    fun getItem3Str(): String? {
        return getDateFormat(item3)
    }
}

fun getDateFormat(date: Date?): String {
    if (date != null) {
        return SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)
    }
    return ""
}