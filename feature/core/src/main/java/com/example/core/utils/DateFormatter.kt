package com.example.core.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDate.formatDate(): String {
    val locale = Locale.getDefault()
    val pattern = SimpleDateFormat("dd MMMM yyyy", locale).toPattern()

    val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, locale)
    return this.format(dateTimeFormatter)
}