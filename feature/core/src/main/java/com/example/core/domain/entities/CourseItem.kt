package com.example.core.domain.entities

import androidx.compose.runtime.Stable
import java.time.LocalDate

@Stable
data class CourseItem(
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: LocalDate,
    val hasLike: Boolean,
    val publishDate: LocalDate
)