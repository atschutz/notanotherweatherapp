package com.example.notanotherweatherapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun getTimeFromDateString(date: String): String {
    val hours = date.safeSlice(11..12).toInt()
    return if (hours == 0) "12am"
        else if (hours > 12) "${hours - 12}pm"
        else "${hours}am"
}

fun String.safeSlice(range: IntRange): String =
    if (this.isEmpty() || range.first < 0 || range.last >= this.length) ""
    else this.slice(range)

// Utility function for removing ripple effect from click events.
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}