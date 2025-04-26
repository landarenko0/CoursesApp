package com.example.courses.remote.mapper

import com.example.courses.entities.Course
import com.example.courses.remote.entities.CourseApi
import java.time.LocalDate

internal fun CourseApi.toDomain(): Course = Course(
    id = this.id,
    title = this.title,
    text = this.text,
    price = this.price,
    rate = this.rate,
    startDate = LocalDate.parse(this.startDate),
    hasLike = this.hasLike,
    publishDate = LocalDate.parse(this.publishDate)
)