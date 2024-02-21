package com.example.notanotherweatherapp

fun getTimeFromDateString(date: String): String {
    val hours = date.safeSlice(11..12).toInt()
    return if (hours == 0) "12am"
        else if (hours > 12) "${hours - 12}pm"
        else "${hours}am"
}

fun String.safeSlice(range: IntRange): String =
    if (this.isEmpty() || range.first < 0 || range.last >= this.length) ""
    else this.slice(range)