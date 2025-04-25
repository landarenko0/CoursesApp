package com.example.domain.entities

import java.time.LocalDate

data class Course(
    val id: Long,
    val title: String,
    val text: String,
    val price: Float,
    val rate: Float,
    val startDate: LocalDate,
    val hasLike: Boolean,
    val publishDate: LocalDate
)