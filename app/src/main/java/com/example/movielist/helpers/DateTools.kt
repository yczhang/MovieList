package com.example.movielist.helpers

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTools {

    fun convertTime(dateString: String?): String {

        if(dateString == null) return "N/A"
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+ss:ss", Locale.US)
        val outputFormat = SimpleDateFormat("MM-dd-yyyy h:mm a", Locale.US)
        return try {
            val date: Date? = inputFormat.parse(dateString)
            if (date == null) {
                ""
            } else outputFormat.format(date)
        } catch (e: ParseException) {
            ""
        }
    }
}